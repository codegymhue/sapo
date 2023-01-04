package vn.sapo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.customer.dto.CusEmployeeResult;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;

import java.time.Instant;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByGroupIdAndStatus(Integer groupId, CustomerStatus status);
//    List<Customer> findAllByFilter(Integer groupTitLeId, CustomerGender gender, CustomerStatus status, CusEmployeeResult employee, Instant createdAt, Instant birthday);

    List<Customer> findAllByGroupId(Integer groupId);


}


