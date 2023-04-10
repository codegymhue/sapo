package vn.sapo.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.contact.dto.DeletedContactResult;
import vn.sapo.entities.Contact;
import vn.sapo.entities.customer.Customer;
import vn.sapo.shared.exceptions.NotFoundException;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class ContactCustomerServiceImpl implements ContactCustomerService {
    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ContactCustomerRepository contactCustomerRepository;

    @Override
    @Transactional
    public DeletedContactResult deleteCustomerContactById(Integer customerId, Set<Long> ids) {
        Customer customer = findCustomerById(customerId);
        Set<Contact> contacts =  customer.getContacts();
        Set<Contact> newContacts = new HashSet<>(contacts);
        List<String> deletedNames = new ArrayList<>();
        int i = 0;
        for (Contact contact : contacts) {
            for (Long id : ids) {
                if (contact.getId().equals(id)){
                    deletedNames.add(contact.getFullName());
                    i++;
                    newContacts.remove(contact);
                }
            }
        }

//        for (Long id :ids) {
//            newContacts.removeIf(contact -> contact.getId().equals(id));
//        }

        customer.setContacts(newContacts);
        contactCustomerRepository.save(customer);

        return new DeletedContactResult()
                .setIdsDeleted(ids)
                .setNamesDeleted(deletedNames)
                .setNumberOfSuccess(i)
                .setNumberOfFail(ids.size() - i);
    }

    @Override
    public Page<ContactResult> findAllContact(Pageable pageable, Integer customerId) {
        List<ContactResult> dtoList = findCustomerById(customerId)
                .getContacts()
                .stream()
                .map(contactMapper::toDTO)
                .sorted((o1, o2) -> (int) (o2.getId() - o1.getId()))
                .collect(Collectors.toList());
        int sizePerPage = pageable.getPageSize();
        int page = pageable.getPageNumber();
        int from = Math.max(0, page * sizePerPage);
        int to = Math.min(dtoList.size(), (page + 1) * sizePerPage);

        List<ContactResult> results = dtoList.subList(from, to);

        return new PageImpl<>(results, pageable, dtoList.size());
    }

    @Override
    @Transactional
    public ContactResult createByCustomerId(Integer id, CreateContactParam createParam) {
        Customer customer = findCustomerById(id);

        Contact contact = contactMapper.toModel(createParam)
                .setId(System.currentTimeMillis())
                .setStatus("ACTIVE")
                .setCreatedAt(Instant.now());

        customer.getContacts().add(contact);

        return contactMapper.toDTO(contact);
    }

    private Customer findCustomerById(Integer id) {
        return contactCustomerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("customer.findById.notFound")
        );
    }
}
