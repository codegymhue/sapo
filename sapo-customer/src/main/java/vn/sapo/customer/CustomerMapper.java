package vn.sapo.customer;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.SourceGetter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.Address;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

import java.time.Instant;

@Component
public class CustomerMapper implements InitializingBean {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        TypeMap<CreateCustomerParam, Customer> dto2Model = modelMapper.createTypeMap(CreateCustomerParam.class, Customer.class);
        dto2Model.addMapping(source -> source.getBirthday().toInstant(), Customer::setBirthday);
    }

    public CustomerResult toDTO(Customer customer) {
        System.out.println(customer);
        return modelMapper.map(customer, CustomerResult.class)
                .setName(customer.getFullName())
                .setPhoneNumber(customer.getPhoneNumber());


    }

    public Customer toModel(CreateCustomerParam createCustomerParam) {
        return modelMapper.map(createCustomerParam, Customer.class)
                .setStatus(CustomerStatus.AVAILABLE);
    }

    public void transferFields(UpdateCustomerParam updateCustomerParam, Customer customer) {
        modelMapper.map(updateCustomerParam, customer);
    }



}