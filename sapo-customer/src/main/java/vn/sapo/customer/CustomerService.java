package vn.sapo.customer;

import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerDebtImpl;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;

import java.util.List;

public interface CustomerService {

    CustomerResult findById(Integer id);

    List<CustomerResult> findAll();

    CustomerResult create(CreateCustomerParam customerCreate);

    //
    CustomerResult update(UpdateCustomerParam updateCustomer);

//    List<SaleOrderResult> findHistoryCustomerOrder(Integer id);

//    List<CustomerDebtImpl> findCustomerDebtsByCustomerId(Integer customerId);

    void deleteById(Integer id);

    boolean existsById(Integer id);
}
