package vn.sapo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByGroupIdAndStatus(Integer groupId, CustomerStatus status);

    List<Customer> findAllByStatus(CustomerStatus status);

    List<Customer> findAllByGroupId(Integer groupId);
}


