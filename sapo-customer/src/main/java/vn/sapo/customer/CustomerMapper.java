package vn.sapo.customer;

import com.phpn.customer.dto.CreateCustomerParam;
import com.phpn.customer.dto.CustomerOrderResult;
import com.phpn.customer.dto.CustomerResult;
import com.phpn.customer.dto.UpdateCustomerParam;
import com.phpn.order.sale.dto.CustomerSaleOrderResult;
import com.phpn.shipping_address.dto.ShippingAddressResult;
import com.phpn.employee.dto.EmployeeMapper;
import vn.fx.qh.sapo.entities.customer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.address.AddressMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private AddressMapper addressMapper;


    public CustomerResult toDTO(Customer customer) {
        CustomerResult result = new CustomerResult()
                .setId(customer.getId())
                .setCustomerCode(customer.getCustomerCode())
                .setName(customer.getName())
                .setPhone(customer.getPhone())
                .setCustomerGroup(customer.getCustomerGroup())
                .setCustomerGender(customer.getCustomerGender())
                .setEmail(customer.getEmail())
                .setBirthday(customer.getBirthday())
                .setCustomerStatus(customer.getCustomerStatus())
                .setEmployeeId(customer.getEmployeeId())
                .setCustomerStatus(customer.getCustomerStatus());

        Set<ShippingAddress> shippingAddressSet = customer.getShippingAddressSet();
        List<ShippingAddressResult> shippingAddressList = shippingAddressSet.stream()
                .map(addressMapper::toDTO).collect(Collectors.toList());
        result.setShippingAddressList(shippingAddressList);

        ShippingAddress shippingAddress = customer.getShippingAddress();
        if (shippingAddress != null)
            result.setShippingAddress(addressMapper.toDTO(customer.getShippingAddress()));

        ShippingAddress receiveBillAddress = customer.getReceiveBillAddress();
        if (receiveBillAddress != null)
            result.setBillAddress(addressMapper.toDTO(customer.getReceiveBillAddress()));

        return result;
    }

    public CustomerOrderResult toOrderDTO(Customer customer) {
        return new CustomerOrderResult()
                .setId(customer.getId())
                .setCustomerCode(customer.getCustomerCode())
                .setName(customer.getName())
                .setPhone(customer.getPhone())
                .setShippingAddressSet(customer.getShippingAddressSet())
                .setEmployeeId(customer.getEmployeeId());
    }

    public CustomerSaleOrderResult toDTOCustomerSaleOrder(Customer customer) {
        return new CustomerSaleOrderResult()
                .setId(customer.getId()).setFullName(customer.getName());
    }

    public Customer toModel(CreateCustomerParam customerCreate) {
        return new Customer()
                .setEmployeeId(customerCreate.getEmployeeId())
                .setCustomerCode(customerCreate.getCustomerCode())
                .setName(customerCreate.getName())
                .setPhone(customerCreate.getPhone())
                .setCustomerGroup(customerCreate.getCustomerGroup())
                .setEmail(customerCreate.getEmail())
                .setBirthday(customerCreate.getBirthday())
                .setCustomerStatus(CustomerStatus.AVAILABLE);
    }

    public Customer toCustomer(CreateCustomerParam customerCreate) {
        return new Customer()
                .setName(customerCreate.getName())
                .setPhone(customerCreate.getPhone())
                .setCustomerGroup(customerCreate.getCustomerGroup())
                .setCustomerGender(customerCreate.getCustomerGender())
                .setEmail(customerCreate.getEmail())
                .setBirthday(customerCreate.getBirthday())
                .setCustomerGender(customerCreate.getCustomerGender())
                .setCustomerStatus(customerCreate.getCustomerStatus())
                .setEmployeeId(customerCreate.getEmployeeId());
    }

    public Customer toCustomer(UpdateCustomerParam updateCustomerParam, Customer customer) {
        return customer
                .setId(updateCustomerParam.getId())
                .setCustomerCode(updateCustomerParam.getCustomerCode())
                .setName(updateCustomerParam.getName())
                .setPhone(updateCustomerParam.getPhone())
                .setCustomerGroup(updateCustomerParam.getCustomerGroup())
                .setCustomerGender(updateCustomerParam.getCustomerGender())
                .setEmail(updateCustomerParam.getEmail())
                .setBirthday(updateCustomerParam.getBirthday())
                .setCustomerStatus(updateCustomerParam.getCustomerStatus());
    }


    public CustomerResult toCustomerInfo(Customer customer) {
        return new CustomerResult()
                .setId(customer.getId())
                .setCustomerCode(customer.getCustomerCode())
                .setName(customer.getName())
                .setPhone(customer.getPhone())
                .setCustomerGroup(customer.getCustomerGroup())
                .setCustomerGender(customer.getCustomerGender())
                .setEmail(customer.getEmail())
                .setBirthday(customer.getBirthday())
                .setCustomerStatus(customer.getCustomerStatus())
                .setEmployeeId(customer.getEmployeeId())
                .setEmployeeResult(employeeMapper.toDTO(customer.getEmployee()))
                .setCustomerStatus(customer.getCustomerStatus());

    }


}