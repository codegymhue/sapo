package vn.sapo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.entities.customer.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//    @Query(value = "call `ph-pn`.sp_getCustomerDebtByCustomerId( :customerId);", nativeQuery = true)
//    List<CustomerDebt> findCustomerDebtsByCustomerId(Integer customerId);
//
//    @Query(" FROM Customer WHERE customerStatus = 'AVAILABLE' ")
//    List<Customer> findCustomersByCustomerStatus();

}


