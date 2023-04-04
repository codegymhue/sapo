package vn.sapo.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.contact.dto.UpdateContactParam;
import vn.sapo.entities.Contact;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.shared.exceptions.NotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactSupplierServiceImpl implements ContactSupplierService {
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ContactSupplierRepository supplierRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ContactResult> findAllContactBySupplierId(Integer supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("supplier.exception.notFound"));
        return supplier.getContacts().stream().map(contactMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ContactResult createContactBySupplierId(Integer supplierId, CreateContactParam createParam) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("supplier.exception.notFound"));
        Contact contact = contactMapper.toModel(createParam);
        contact.setId(System.currentTimeMillis());
        contact.setStatus("ACTIVE");
        contact.setCreatedAt(Instant.now());
        supplier.getContacts().add(contact);
        return contactMapper.toDTO(contact);
    }

    @Override
    public void createContactListBySupplierId(Integer supplierId, List<CreateContactParam> createParams) {

    }

    @Override
    @Transactional
    public ContactResult updateContactBySupplierId(Integer supplierId, UpdateContactParam updateParam) {
        Supplier supplier = null;//= supplierRepository.findByIdAndContactId(supplierId)
        //  .orElseThrow(() -> new NotFoundException("supplier.exception.notFound"));
        Contact contact = supplier.getContacts().stream()
                .filter(c -> c.getId().equals(updateParam.getId()))
                .findAny()
                .orElseThrow(() -> new NotFoundException("contact.exception.notFound"));

        contactMapper.transferFields(updateParam, contact);
        contact.setId(System.currentTimeMillis());
        contact.setUpdatedAt(Instant.now());
        supplier.getContacts().add(contact);
        return contactMapper.toDTO(contact);
    }
}
