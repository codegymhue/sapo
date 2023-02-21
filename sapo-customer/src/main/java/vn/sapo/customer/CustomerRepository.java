package vn.sapo.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.sapo.customer.dto.CusEmployeeResult;
import vn.sapo.customer.dto.CustomerFilter;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.Address;
import vn.sapo.entities.Employee;

import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.Instant;
import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
    List<Customer> findAllByGroupIdIn(List<Integer> groupIds);

    List<Customer> findAllByEmployeeIdIn(List<Integer> employeeIds);

    List<Customer> findAllByGender(String genderId);


    List<Customer> findAllByGroupIdAndStatus(Integer groupTitleId, CustomerStatus parseCustomerGroup);
}


