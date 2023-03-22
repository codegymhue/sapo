package vn.sapo.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.customer.dto.*;

import java.util.List;

public interface CustomerService {
    CustomerResult findById(Integer id);

    List<CustomerResult> findAll();

    CustomerResult create(CreateCustomerParam customerCreate);

    CustomerResult update(Integer id, UpdateCustomerParam updateCustomer);

    CustomerResult updateSeries(CustomerUpdateSeries customerUpdateSeries);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    CustomerResult changeStatusToAvailable(Integer customerIds, boolean status);
    List<CustomerResult> findAllByGroupListId(List<Integer> groupIds);
    Page<CustomerResult> findAllByFilters(CustomerFilter filters, Pageable pageable);
}
