package vn.sapo.customerGroup;

import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
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
}
