package vn.sapo.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.customer.Customer;

@Repository
public interface ContactCustomerRepository extends JpaRepository<Customer, Integer> {
    String findContactById(@Param("customerId") Integer customerId);

}
