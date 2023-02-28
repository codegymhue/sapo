package vn.sapo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
    List<Customer> findAllByGroupIdIn(List<Integer> groupIds);

    List<Customer> findAllByGroupIdAndStatus(Integer groupTitleId, CustomerStatus parseCustomerGroup);
}


