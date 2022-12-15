package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<CustomerResult> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


//    @GetMapping("/findAllByStatus")
//    public ResponseEntity<?> findAllByStatus() {
//        List<CustomerResult> customers = customerService.findCustomerByStatus();
//        return new ResponseEntity<>(customers, HttpStatus.OK);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerResult customerResult = customerService.findById(id);
        return new ResponseEntity<>(customerResult, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Integer id) {
        customerService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCustomerParam createCustomerParam) {
        CustomerResult dto = customerService.create(createCustomerParam);
        CreateAddressParam createAddressParam = createCustomerParam.getCreateAddressParam();
        if (createAddressParam == null)
            return new ResponseEntity<>(dto, HttpStatus.OK);
        createAddressParam.setCustomerId(dto.getId());
        addressService.create(createAddressParam);
        dto = customerService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerParam updateCustomer) {
        return new ResponseEntity<>(customerService.update(updateCustomer), HttpStatus.OK);
    }
//
//
//    @GetMapping("/customerGroup")
//    public CustomerGroup[] findAllByCustomerGroup() {
//        CustomerGroup[] customerGroups = CustomerGroup.values();
//        System.out.println(customerGroups);
//        return customerGroups;
//    }
//
//    @GetMapping("/customerGender")
//    public CustomerGender[] findAllByCustomerGender() {
//        CustomerGender[] customerGender = CustomerGender.values();
//        return customerGender;
//    }
//
//    @GetMapping("/customerStatus")
//    public CustomerStatus[] findAllByCustomerStatus() {
//        CustomerStatus[] customerStatuses = CustomerStatus.values();
//        return customerStatuses;
//    }


//    @GetMapping("/showAllCustomerMixInfo")
//    public ResponseEntity<?> showAllCustomerMixInfo() {
//        List<ICustomer> iCustomers = customerService.showAllCustomerMixInfo();
//        return new ResponseEntity<>(iCustomers, HttpStatus.OK);
//    }
//
//    @GetMapping("/showAllCustomerMixInfoByStatus")
//    public ResponseEntity<?> showAllCustomerMixInfoByStatus() {
//        List<ICustomer> iCustomers = customerService.showAllCustomerMixInfoByStatus();
//        return new ResponseEntity<>(iCustomers, HttpStatus.OK);
//    }
//
//    @GetMapping("/showAllShippingAddress/{id}")
//    public ResponseEntity<?> showListCustomerInfo(@PathVariable Integer id) {
//        -=]
//
//        return new ResponseEntity<>(iCustomer, HttpStatus.OK);
//    }
//
//    @GetMapping("/historyCustomerOrder/{id}")
//    public ResponseEntity<?> showListCustomerOrderById(@PathVariable Integer id) {
//        List<SaleOrderResult> saleOrderByCustomers = customerService.findHistoryCustomerOrder(id);
//        return new ResponseEntity<>(saleOrderByCustomers, HttpStatus.OK);
//    }


//    @GetMapping("/customerDebt/{id}")
//    public ResponseEntity<?> showListCustomerDebtById(@PathVariable Integer id) {
//        List<ICustomerOwerImpl> iCustomerImpls = customerService.CustomerOwerById(id);
//        return new ResponseEntity<>(iCustomerImpls, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/getQuantityOrderByCustomer/{id}")
//    @Transactional(readOnly = true)
//    public ResponseEntity<?> getQuantityOrderByCustomer(@PathVariable Integer id) {
//        Integer iCustomerImpls = customerRepository.getQuantityOrderByCustomer(id);
//        return new ResponseEntity<>(iCustomerImpls, HttpStatus.OK);
//    }


//    @GetMapping("/customerDebt/{id}")
//    public ResponseEntity<?> findCustomerDebtById(@PathVariable Integer id) {
//        List<CustomerDebtImpl> customerDebtImpl = customerService.findCustomerDebtsByCustomerId(id);
//        return new ResponseEntity<>(customerDebtImpl, HttpStatus.OK);
//    }
}

