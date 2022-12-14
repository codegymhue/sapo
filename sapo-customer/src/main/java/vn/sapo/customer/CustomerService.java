package vn.sapo.customer;

import com.phpn.customer.customerDebt.CustomerDebt;
import com.phpn.customer.customerDebt.CustomerDebtImpl;
import com.phpn.customer.dto.CustomerResult;
import com.phpn.customer.dto.UpdateCustomerParam;
import com.phpn.customer.dto.CreateCustomerParam;
import com.phpn.order.sale.dto.SaleOrderResult;

import java.util.List;

public interface CustomerService {

    CustomerResult findById(Integer id);

    List<CustomerResult> findAll();

    List<CustomerResult> findCustomerByStatus();

    CustomerResult create(CreateCustomerParam customerCreate);

    CustomerResult update(UpdateCustomerParam updateCustomer);

    List<SaleOrderResult> findHistoryCustomerOrder(Integer id);

    List<CustomerDebtImpl> findCustomerDebtsByCustomerId(Integer customerId);

    void delete(Integer customerId);

}
