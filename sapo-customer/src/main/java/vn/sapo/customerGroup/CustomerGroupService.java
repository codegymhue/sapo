package vn.sapo.customerGroup;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.customerGroup.dto.*;
=======
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.ICustomerGroup;
import vn.sapo.customerGroup.dto.UpdateCusGroupParam;
>>>>>>> cf9e56958713b8ee99a298cabdf1ddcf9488ab60

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
