package vn.sapo.customerGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import vn.sapo.customerGroup.dto.*;

import java.util.List;

public interface CustomerGroupService {

    CustomerGroupResult findCustomerGroupByCustomerId(Integer id);

    CustomerGroupResult create(CreateCusGroupParam createCusGroupParam);

    CustomerGroupResult update(Integer id, UpdateCusGroupParam updateCusGroupParam);

    List<CustomerGroupResult> findAll();

    CustomerGroupResult findById(Integer id);

    void deleteById(Integer id);

    Page<ICustomerGroupResult> findAllCustomerGroupPageable(Pageable pageable);

    Page<ICustomerGroupResult> getAllCustomerGroupsPagination(DataTablesInput input);
}
