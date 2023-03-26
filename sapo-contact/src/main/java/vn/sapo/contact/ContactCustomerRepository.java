package vn.sapo.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.entities.customer.Customer;

@Repository
public interface ContactCustomerRepository extends JpaRepository<Customer, Integer> {

    Page<ContactResult> findAllByCustomerCode(String code, Pageable pageable);
}
