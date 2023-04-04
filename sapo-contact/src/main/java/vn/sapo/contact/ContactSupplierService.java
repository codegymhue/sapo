package vn.sapo.contact;

import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.contact.dto.UpdateContactParam;

import java.util.List;

public interface ContactSupplierService {
    List<ContactResult> findAllContactBySupplierId(Integer supplierId);

    ContactResult createContactBySupplierId(Integer supplierId, CreateContactParam createParam);

    void createContactListBySupplierId(Integer supplierId, List<CreateContactParam> createParams);

    ContactResult updateContactBySupplierId(Integer supplierId, UpdateContactParam updateParam);
}
