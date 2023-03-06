package vn.sapo.customerGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.customerGroup.dto.*;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.ICustomerGroup;
import vn.sapo.customerGroup.dto.UpdateCusGroupParam;

import java.util.*;

public interface CustomerGroupService {
    CustomerGroupResult create(CreateCusGroupParam createCusGroupParam);

    CustomerGroupResult update(UpdateCusGroupParam updateCusGroupParam);

    List<CustomerGroupResult> findAll();

    List<ICustomerGroup> sortByGroup();


    CustomerGroupResult findById(Integer id);

    void deleteById(Integer id);

    Page<CustomerGroupResult> findAllByFilters(CustomerGroupFilter filters, Pageable pageable);
}
