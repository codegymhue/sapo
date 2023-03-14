package vn.sapo.customerGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.customerGroup.dto.*;

import java.util.List;

public interface CustomerGroupService {

    List<CustomerGroupResult> findAllCustomerGroupResult();

    CustomerGroupResult create(CreateCusGroupParam createCusGroupParam);

    CustomerGroupResult update(Integer id, UpdateCusGroupParam updateCusGroupParam);

    List<CustomerGroupResult> findAll();

    List<ICustomerGroupResult> sortByGroup();

    CustomerGroupResult findById(Integer id);

    void deleteById(Integer id);

    Page<CustomerGroupResult> findAllByFilters(CustomerGroupFilter filters, Pageable pageable);
}
