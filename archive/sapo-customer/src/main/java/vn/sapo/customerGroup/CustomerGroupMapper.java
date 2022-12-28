package vn.sapo.customerGroup;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.CustomerGroup;


@Component
public class CustomerGroupMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerGroupResult toDTO(CustomerGroup customerGroup) {
        return modelMapper.map(customerGroup, CustomerGroupResult.class);
    }

}
