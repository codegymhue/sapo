package vn.sapo.customer;

import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;

import java.util.List;

public interface CustomerService {
    CustomerResult findById(Integer id);
    List<CustomerResult> findAll();
    CustomerResult create(CreateCustomerParam customerCreate);
    CustomerResult update(UpdateCustomerParam updateCustomer);
    void deleteById(Integer id);
    boolean existsById(Integer id);
    void changeStatusToAvailable(List<Integer> customerIds, boolean status);


    List<CustomerResult> findAllByGroupListId(List<Integer> groupIds);

    List<CustomerResult> findAllEmployeeListId(List<Integer> employeeIds);

    List<CustomerResult> findAllByGenderId(String genderId);




//    List<CustomerResult> findAllByStatusListId(List<String> statusIds);

}
