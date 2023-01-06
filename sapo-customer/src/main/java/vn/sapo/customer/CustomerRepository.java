package vn.sapo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.customer.dto.CusEmployeeResult;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.entities.customer.CustomerStatus;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByGroupIdIn(List<Integer> groupIds);
    List<Customer> findAllByEmployeeIdIn(List<Integer> employeeIds);

    List<Customer> findAllByGender(String genderId);

//    List<Customer> findAllByStatusIdIn(List<String> StatusIds);
}


