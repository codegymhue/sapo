package vn.sapo.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.contact.dto.DeletedContactResult;

import java.util.Set;

public interface ContactCustomerService {

    DeletedContactResult deleteCustomerContactById(Integer customerId, Set<Long> ids);

    Page<ContactResult> findAllContact(Pageable pageable, Integer customerId);

    ContactResult createByCustomerId(Integer id, CreateContactParam createParam);
}
