package vn.sapo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    List<Customer> findAllByGroupIdIn(List<Integer> groupIds);

    List<Customer> findAllByGroupIdAndStatus(Integer groupTitleId, CustomerStatus parseCustomerGroup);

    List<Customer> findAllByEmployeeIdIn(List<Integer> employeeIds);
    List<Customer> findAllByGender(String genderId);
    @Modifying
    @Query(value = "UPDATE `sapo`.`customer` SET `employee_id` = :employeeId, `default_payment_method_id` = :paymentMethodId, `default_pricing_policy_id` = :pricingPolicyId WHERE (`id` = :id)", nativeQuery = true)
    void updateSeriesCustomer(@Param("employeeId") Integer employeeId, @Param("paymentMethodId") String paymentMethodId, @Param("pricingPolicyId") Integer pricingPolicyId, @Param("id") Integer id);

}


