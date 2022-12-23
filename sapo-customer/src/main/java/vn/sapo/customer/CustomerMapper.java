package vn.sapo.customer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerOrderResult;
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
//        modelMapper.createTypeMap(Customer.class, CustomerResult.class);
//        modelMapper.createTypeMap(CreateCustomerParam.class, Address.class);
    }


    public CustomerResult toDTO(Customer customer) {
        System.out.println(customer);
        return modelMapper.map(customer, CustomerResult.class)
                .setName(customer.getFullName())
                .setPhoneNumber(customer.getPhoneNumber());
    }

    public CustomerOrderResult toOrderDTO(Customer customer) {
        return modelMapper.map(customer, CustomerOrderResult.class)
                .setName(customer.getFullName())
                .setPhone(customer.getPhoneNumber());
    }

    public Customer toModel(CreateCustomerParam createCustomerParam) {
        return modelMapper.map(createCustomerParam, Customer.class)
                .setStatus(CustomerStatus.AVAILABLE);
    }

    public void transferFields(UpdateCustomerParam updateCustomerParam, Customer customer) {
        modelMapper.map(updateCustomerParam, customer);
    }


//    public CustomerOrderResult toOrderDTO(Customer customer) {
//        return new CustomerOrderResult()
//                .setId(customer.getId())
//                .setCustomerCode(customer.getCustomerCode())
//                .setName(customer.getName())
//                .setPhone(customer.getPhone())
//                .setShippingAddressSet(customer.getShippingAddressSet())
//                .setEmployeeId(customer.getEmployeeId());
//    }
//
//    public CustomerSaleOrderResult toDTOCustomerSaleOrder(Customer customer) {
//        return new CustomerSaleOrderResult()
//                .setId(customer.getId()).setFullName(customer.getName());
//    }
//

//    public Customer toCustomer(CreateCustomerParam customerCreate) {
//        return new Customer()
//                .setName(customerCreate.getName())
//                .setPhone(customerCreate.getPhone())
//                .setCustomerGroup(customerCreate.getCustomerGroup())
//                .setCustomerGender(customerCreate.getCustomerGender())
//                .setEmail(customerCreate.getEmail())
//                .setBirthday(customerCreate.getBirthday())
//                .setCustomerGender(customerCreate.getCustomerGender())
//                .setCustomerStatus(customerCreate.getCustomerStatus())
//                .setEmployeeId(customerCreate.getEmployeeId());
//    }
//
//    public Customer toCustomer(UpdateCustomerParam updateCustomerParam, Customer customer) {
//        return customer
//                .setId(updateCustomerParam.getId())
//                .setCustomerCode(updateCustomerParam.getCustomerCode())
//                .setName(updateCustomerParam.getName())
//                .setPhone(updateCustomerParam.getPhone())
//                .setCustomerGroup(updateCustomerParam.getCustomerGroup())
//                .setCustomerGender(updateCustomerParam.getCustomerGender())
//                .setEmail(updateCustomerParam.getEmail())
//                .setBirthday(updateCustomerParam.getBirthday())
//                .setCustomerStatus(updateCustomerParam.getCustomerStatus());
//    }
//
//
//    public CustomerResult toCustomerInfo(Customer customer) {
//        return new CustomerResult()
//                .setId(customer.getId())
//                .setCustomerCode(customer.getCustomerCode())
//                .setName(customer.getName())
//                .setPhone(customer.getPhone())
//                .setCustomerGroup(customer.getCustomerGroup())
//                .setCustomerGender(customer.getCustomerGender())
//                .setEmail(customer.getEmail())
//                .setBirthday(customer.getBirthday())
//                .setCustomerStatus(customer.getCustomerStatus())
//                .setEmployeeId(customer.getEmployeeId())
//                .setEmployeeResult(employeeMapper.toDTO(customer.getEmployee()))
//                .setCustomerStatus(customer.getCustomerStatus());
//
//    }


}