package vn.sapo.address;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;
import vn.sapo.customer.CustomerFilterRepository;
import vn.sapo.customer.CustomerMapper;
import vn.sapo.customer.CustomerRepository;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.entities.Address;
import vn.sapo.entities.customer.Customer;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(CustomerConfigurationTest.class)
public class CustomerServiceTest {

    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    CustomerFilterRepository customerFilterRepository;
    @MockBean
    AddressService addressService;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerService customerService;

    private static Customer customer;
    private static CustomerResult customerResult;
    private static CreateCustomerParam createCustomerParam;
    @BeforeAll
    static void init(){
        customer = CustomerDTOTest.getCustomer().get(0);
        customerResult = CustomerDTOTest.getCustomerResult().get(0);
        createCustomerParam = CustomerDTOTest.getCreateCustomerParam();
    }
    @BeforeEach
    public void setData(){
        when(customerRepository.save(isA(Customer.class))).thenReturn(customer);
    }
    @Test
    public void testCreateCustomer(){
        CustomerResult customerResult = customerService.create(createCustomerParam);
        CustomerAssertTest.assertCustomer(customerResult,customer);
    }

}
