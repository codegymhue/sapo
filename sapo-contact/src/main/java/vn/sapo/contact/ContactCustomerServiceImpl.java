package vn.sapo.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.entities.Contact;
import vn.sapo.entities.customer.Customer;
import vn.sapo.shared.exceptions.NotFoundException;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactCustomerServiceImpl implements ContactCustomerService {
    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ContactCustomerRepository contactCustomerRepository;

    @Override
    public Page<ContactResult> findAllContact(Pageable pageable, Integer customerId) {
        Customer customer = findCustomerById(customerId);
        List<ContactResult> dtoList = customer.getContacts().stream().map(contactMapper::toDTO).collect(Collectors.toList());
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

//    private HashMap<String, String> getContactParamValue(Contact contact) {
//        HashMap<String, String> contacts = new HashMap<>();
//
//        contacts.put("id", String.valueOf(contact.getId()));
//        contacts.put("fullName", contact.getFullName());
//        contacts.put("phoneNumber", contact.getPhoneNumber());
//        contacts.put("email", contact.getEmail());
//        contacts.put("fax", contact.getFax());
//        contacts.put("position", contact.getPosition());
//        contacts.put("department", contact.getDepartment());
//        contacts.put("note", contact.getNote());
//
//        return contacts;
//    }

    private Customer findCustomerById(Integer id) {
        return contactCustomerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("customer.findById.notFound")
        );
    }
}
