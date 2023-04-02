package vn.sapo.customer.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.CustomerServiceImpl;
import vn.sapo.customers.dto.CreateAddressParam;

import java.util.HashMap;
import java.util.List;
@Setter
@Getter
@Component
public class CreateCustomerThread implements Runnable{
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CreateSeriesCustomerParam createSeriesCustomerParam;
    private Thread t;

    @Override
    public void run() {
        customerService.createSeriesCustomer(createSeriesCustomerParam);
    }
    public void start(CreateSeriesCustomerParam createSeriesCustomerParam){
        t = new Thread(this);
        this.createSeriesCustomerParam = createSeriesCustomerParam;
        t.start();
    }
    @Override
    public String toString() {
        return "CreateCustomerThread{" +
                "customerService=" + customerService +
                ", createSeriesCustomerParam=" + createSeriesCustomerParam +
                '}';
    }
}
