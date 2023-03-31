package vn.sapo.customers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.customers.dto.UpdateAddressParam;

import java.util.List;
import java.util.Map;

public interface AddressService {

    Page<AddressResult> findAllAddresses(Integer id, Pageable pageable);

    AddressResult createAddressWithCustomerId(CreateAddressParam createAddressParam, Integer id);

    AddressResult findById(Integer id);

    List<AddressResult> findByCustomerId(Integer customerId);

    AddressResult create(CreateAddressParam createShippingAddressParam);

    void create(List<CreateAddressParam> createShippingAddressParams);

    void deleteByCustomerId(Integer customerId);

    List<AddressResult> findAll();

    AddressResult update(UpdateAddressParam updateAddressParam);

    List<AddressResult> findBySupplierId(Integer supplierId);

    void deleteByAddressSupplierId(Integer idAddressSupplier);

    void deleteSoftSupplier(List<Integer> supplierIds);


    Map<String, Object> getAllAddressSupplierPage(Integer pageNo, Integer pageSize, Integer supplierId);

    void deleteAllBySupplierId(Integer id);
}
