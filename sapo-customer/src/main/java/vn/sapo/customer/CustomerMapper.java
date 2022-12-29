package vn.sapo.customer;

import org.modelmapper.ModelMapper;
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