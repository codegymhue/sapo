package vn.sapo.address;


import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;

import java.util.List;

public interface AddressService {

    List<AddressResult> findByCustomerId(Integer customerId);

    AddressResult create(CreateAddressParam createShippingAddressParam);

    void deleteByCustomerId(Integer customerId);

    List<AddressResult> findAll();

    AddressResult update(UpdateAddressParam updateAddressParam);
}
