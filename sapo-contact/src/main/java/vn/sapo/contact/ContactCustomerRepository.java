package vn.sapo.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.contact.dto.UpdateContactParam;
import vn.sapo.entities.Contact;
import vn.sapo.entities.customer.Customer;

import java.util.Set;

@Repository
public interface ContactCustomerRepository extends JpaRepository<Customer, Integer> {

}
