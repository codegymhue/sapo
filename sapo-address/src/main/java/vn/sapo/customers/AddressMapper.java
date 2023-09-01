package vn.sapo.customers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vn.rananu.shared.mappers.BaseMapper;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.customers.dto.BaseAddress;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.customers.dto.UpdateAddressParam;
import vn.sapo.entities.Address;

import static vn.sapo.shared.configurations.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_DISABLED;
import static vn.sapo.shared.configurations.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_ENABLED;

@Component
public class AddressMapper extends BaseMapper<AddressResult,Address, BaseAddress> {
//    @Autowired
//    @Qualifier(MODEL_MAPPER_SKIP_NULL_ENABLED)
//    private ModelMapper modelMapper;
//    @Autowired
//    @Qualifier(MODEL_MAPPER_SKIP_NULL_DISABLED)
//    private ModelMapper modelMapperSkipNullDisabled;

    @Override
    public void afterPropertiesSet()  {
        TypeMap<Address, AddressResult> model2Dto = modelMapper.createTypeMap(Address.class, AddressResult.class);

        model2Dto.addMapping(Address::isShippingAddress, AddressResult::setShipping);

        TypeMap<CreateAddressParam, Address> dto2Model = modelMapper.createTypeMap(CreateAddressParam.class, Address.class);

        dto2Model.addMapping(CreateAddressParam::isShipping, Address::setShippingAddress);
    }

//    public AddressResult toDTO(Address address) {
//        return modelMapper.map(address, AddressResult.class);
//    }
//
//    public Address toModel(CreateAddressParam createAddressParam) {
//        return modelMapper.map(createAddressParam, Address.class);
//    }
//
//    public Address toModel(UpdateAddressParam updateAddressParam) {
//        return modelMapper.map(updateAddressParam, Address.class);
//    }
}
