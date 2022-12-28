package vn.sapo.customerGroup;

import vn.sapo.customerGroup.dto.CustomerGroupResult;

import java.util.*;

public interface CustomerGroupService {
    List<CustomerGroupResult> findAll();

    CustomerGroupResult findById(Integer id);
}
