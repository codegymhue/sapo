package vn.sapo.address;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;
import vn.sapo.entities.Address;

@Component
public class AddressMapper implements InitializingBean {
    @Autowired
    private ModelMapper modelMapper;
    private TypeMap<Address, AddressResult> model2Dto;
//    private TypeMap<AddressResult, Address> dto2Model;

    @Override
    public void afterPropertiesSet() throws Exception {
        model2Dto = modelMapper.createTypeMap(Address.class, AddressResult.class);
//        dto2Model = modelMapper.createTypeMap(AddressResult.class, Address.class);
    }

    public AddressResult toDTO(Address address) {
        model2Dto.addMapping(Address::isShippingAddress, AddressResult::setShipping);
        return modelMapper.map(address, AddressResult.class);
    }

    public Address toModel(CreateAddressParam createAddressParam) {
        return modelMapper.map(createAddressParam, Address.class);
    }

    public Address toModel(UpdateAddressParam updateAddressParam) {
        return modelMapper.map(updateAddressParam, Address.class);
    }
}
