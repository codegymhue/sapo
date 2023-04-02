package vn.sapo.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.customer.dto.*;
import vn.sapo.customers.dto.CreateAddressParam;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {

    CustomerResult findById(Integer id);

    List<CustomerResult> findAll();

    CustomerResult create(CreateCustomerParam customerCreate);

    List<CreateCustomerParam> excelToCustomerCreate(MultipartFile file);
    CustomerResult update(Integer id, UpdateCustomerParam updateCustomer);

    CustomerResult updateSeries(CustomerUpdateSeries customerUpdateSeries);

    CustomerResult deleteById(Integer id);

    boolean existsById(Integer id);

    CustomerResult changeStatusToAvailable(Integer customerIds, boolean status);
    List<CustomerResult> findAllByGroupListId(List<Integer> groupIds);
    Page<CustomerResult> findAllByFilters(CustomerFilter filters, Pageable pageable);
    void createSeriesCustomer(CreateSeriesCustomerParam createSeriesCustomerParam);
}
