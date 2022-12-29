package vn.sapo.customer;

import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    CustomerResult findById(Integer id);

    List<CustomerResult> findAll();

    @Transactional(readOnly = true)

    CustomerResult create(CreateCustomerParam customerCreate);
    CustomerResult update(UpdateCustomerParam updateCustomer);


    void deleteById(Integer id);

    boolean existsById(Integer id);

    @Transactional
    void changeStatusToAvailable(List<Integer> customerIds, boolean status);

    List<CustomerResult> findAllCustomerByGroupAndStatus(Integer groupTitleId, String customerStatus);


}
