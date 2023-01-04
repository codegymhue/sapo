package vn.sapo.customer;

import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CusEmployeeResult;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.CustomerGender;

import java.time.Instant;
import java.util.List;

public interface CustomerService {
    CustomerResult findById(Integer id);
    List<CustomerResult> findAll();
    CustomerResult create(CreateCustomerParam customerCreate);
    CustomerResult update(UpdateCustomerParam updateCustomer);
    void deleteById(Integer id);
    boolean existsById(Integer id);
    void changeStatusToAvailable(List<Integer> customerIds, boolean status);
    List<CustomerResult> findAllCustomerByGroupAndStatus(Integer groupTitleId, String status);

//    List<CustomerResult> findAllByFilter(Integer groupTitLeId, CustomerGender gender, String status, CusEmployeeResult employee, Instant createdAt,Instant birthday);

    List<CustomerResult> findAllByGroupId(Integer groupTitleId);
}
