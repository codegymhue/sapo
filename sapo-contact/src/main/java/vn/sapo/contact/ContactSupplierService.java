package vn.sapo.contact;

import org.springframework.transaction.annotation.Transactional;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.contact.dto.UpdateContactParam;

import java.util.List;

public interface ContactSupplierService {
    @Transactional(readOnly = true)
    List<ContactResult> findContactsBySupplierId(Integer supplierId);

    @Transactional
    ContactResult createContactBySupplierId(Integer supplierId, CreateContactParam createParam);

    @Transactional
    ContactResult updateContactBySupplierId(Integer supplierId, UpdateContactParam updateParam);
}
