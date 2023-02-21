package vn.sapo.customerGroup;

import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.ICustomerGroupResult;
import vn.sapo.customerGroup.dto.UpdateCustomerGroupParam;

import java.util.*;

public interface CustomerGroupService {
    CustomerGroupResult create(CreateCusGroupParam createParam);

    CustomerGroupResult update(UpdateCustomerGroupParam updateParam);

    List<CustomerGroupResult> findAll();

    List<ICustomerGroupResult> sortByGroup();

    CustomerGroupResult findById(Integer id);

    void deleteById(Integer id);
}
