package vn.sapo.customer;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

@Component
public class CustomerMapper implements InitializingBean {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        TypeMap<CreateCustomerParam, Customer> createDTO2Model = modelMapper.createTypeMap(CreateCustomerParam.class, Customer.class);
        createDTO2Model.addMapping(source -> source.getBirthday().toInstant(), Customer::setBirthday);
        TypeMap<UpdateCustomerParam, Customer> updateDTO2Model = modelMapper.createTypeMap(UpdateCustomerParam.class, Customer.class);
        updateDTO2Model.addMapping(source -> source.getBirthday().toInstant(), Customer::setBirthday);
    }

    public CustomerResult toDTO(Customer customer) {
            return modelMapper.map(customer, CustomerResult.class);
    }

    public Customer toModel(CreateCustomerParam createCustomerParam) {
        return modelMapper.map(createCustomerParam, Customer.class)
                .setStatus(CustomerStatus.AVAILABLE);
    }

    public void transferFields(UpdateCustomerParam updateCustomerParam, Customer customer) {
        modelMapper.map(updateCustomerParam, customer);
    }
}