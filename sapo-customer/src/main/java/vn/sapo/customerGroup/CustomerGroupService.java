package vn.sapo.customerGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.customerGroup.dto.*;

import java.util.*;

public interface CustomerGroupService {
    CustomerGroupResult create(CreateCusGroupParam createParam);

    CustomerGroupResult update(UpdateCustomerGroupParam updateParam);

    List<CustomerGroupResult> findAll();

    List<ICustomerGroupResult> sortByGroup();

    CustomerGroupResult findById(Integer id);

    void deleteById(Integer id);

    Page<CustomerGroupResult> findAllByFilters(CustomerGroupFilter filters, Pageable pageable);
}
