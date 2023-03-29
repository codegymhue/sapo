package vn.sapo.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;

public interface ContactCustomerService {

    Page<ContactResult> findAllContact(Integer id, Pageable pageable);

    ContactResult createByCustomerId(Integer id, CreateContactParam createParam);
}
