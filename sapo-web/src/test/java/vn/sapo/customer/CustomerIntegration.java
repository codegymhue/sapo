package vn.sapo.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.sapo.controllers.address.AddressAPI;
import vn.sapo.controllers.customer.CustomerAPI;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
<<<<<<< HEAD
=======
>>>>>>> vt_dev

@SpringBootTest
public class CustomerIntegration {
    @Autowired
    AddressAPI addressAPI;
    @Autowired
    CustomerAPI customerAPI;
    private static CustomerResult customerResultExpected;
    @BeforeEach
    public void init(){
        CreateCustomerParam createCustomerParam = CustomerParamTest.getCreateCustomerParam();
        customerResultExpected = (CustomerResult) customerAPI.create(createCustomerParam).getBody();
    }
    @Test
    public void findById(){
        CustomerResult customerResultActual = (CustomerResult) customerAPI.findById(customerResultExpected.getId()).getBody();
        CustomerAssertTest.assertCustomerResult(customerResultActual, customerResultExpected);
    }
}
