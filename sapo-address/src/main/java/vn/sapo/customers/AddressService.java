package vn.sapo.customers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.sapo.customers.dto.*;

import java.util.List;
import java.util.Map;

public interface AddressService {

    DeleteAddressResult deleteAddressesByListId(Integer id, List<Integer> listAddressesId);

    Page<IAddressResult> findAllAddresses(Pageable pageable, Integer id);

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
