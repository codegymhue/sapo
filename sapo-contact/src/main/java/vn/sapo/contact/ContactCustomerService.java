package vn.sapo.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.contact.dto.DeletedContactResult;
import vn.sapo.contact.dto.UpdateContactParam;

import java.util.Set;

public interface ContactCustomerService {

    ContactResult updateCustomerContactById(Integer customerId, UpdateContactParam updateContactParam);

    ContactResult getCustomerContactById(Integer customerId, Long id);

    DeletedContactResult deleteCustomerContactById(Integer customerId, Set<Long> ids);

    Page<ContactResult> findAllContact(Pageable pageable, Integer customerId);

    ContactResult createByCustomerId(Integer id, CreateContactParam createParam);
}
