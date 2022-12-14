//package vn.sapo.controllers.customer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import vn.sapo.customer.CustomerService;
//import vn.sapo.customer.dto.CustomerResult;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/customers")
//public class CustomerAPI {
//    @Autowired
//    AppUtil appUtil;
//
//    @Autowired
//    private CustomerService customerService;
//
//    @GetMapping
//    public ResponseEntity<?> findAll() {
//        List<CustomerResult> customers = customerService.findAll();
//        return new ResponseEntity<>(customers, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/findCustomerByStatus")
//    public ResponseEntity<?> findCustomerByStatus() {
//        List<CustomerResult> customers = customerService.findCustomerByStatus();
//        return new ResponseEntity<>(customers, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable Integer id) {
//        CustomerResult customerResult = customerService.findById(id);
//        return new ResponseEntity<>(customerResult, HttpStatus.OK);
//    }
//
//
//    @PostMapping("/delete/{id}")
//    public void deleteCustomerById(@PathVariable Integer id) {
//        try {
//            customerService.delete(id);
//        } catch (Exception e) {
//            throw new DataInputException("Lỗi không xác định ");
//        }
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createCustomer( @RequestBody CreateCustomerParam customerCreate) {
//        return new ResponseEntity<>(customerService.create(customerCreate), HttpStatus.OK);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerParam updateCustomer) {
//        return new ResponseEntity<>(customerService.update(updateCustomer), HttpStatus.OK);
//    }
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
//
//
////    @GetMapping("/showAllCustomerMixInfo")
////    public ResponseEntity<?> showAllCustomerMixInfo() {
////        List<ICustomer> iCustomers = customerService.showAllCustomerMixInfo();
////        return new ResponseEntity<>(iCustomers, HttpStatus.OK);
////    }
////
////    @GetMapping("/showAllCustomerMixInfoByStatus")
////    public ResponseEntity<?> showAllCustomerMixInfoByStatus() {
////        List<ICustomer> iCustomers = customerService.showAllCustomerMixInfoByStatus();
////        return new ResponseEntity<>(iCustomers, HttpStatus.OK);
////    }
////
////    @GetMapping("/showAllShippingAddress/{id}")
////    public ResponseEntity<?> showListCustomerInfo(@PathVariable Integer id) {
////        -=]
////
////        return new ResponseEntity<>(iCustomer, HttpStatus.OK);
////    }
//
//    @GetMapping("/historyCustomerOrder/{id}")
//    public ResponseEntity<?> showListCustomerOrderById(@PathVariable Integer id) {
//        List<SaleOrderResult> saleOrderByCustomers = customerService.findHistoryCustomerOrder(id);
//        return new ResponseEntity<>(saleOrderByCustomers, HttpStatus.OK);
//    }
//
//
//
////    @GetMapping("/customerDebt/{id}")
////    public ResponseEntity<?> showListCustomerDebtById(@PathVariable Integer id) {
////        List<ICustomerOwerImpl> iCustomerImpls = customerService.CustomerOwerById(id);
////        return new ResponseEntity<>(iCustomerImpls, HttpStatus.OK);
////    }
////
////
////    @GetMapping("/getQuantityOrderByCustomer/{id}")
////    @Transactional(readOnly = true)
////    public ResponseEntity<?> getQuantityOrderByCustomer(@PathVariable Integer id) {
////        Integer iCustomerImpls = customerRepository.getQuantityOrderByCustomer(id);
////        return new ResponseEntity<>(iCustomerImpls, HttpStatus.OK);
////    }
//
//
//    @GetMapping("/customerDebt/{id}")
//    public ResponseEntity<?> findCustomerDebtById(@PathVariable Integer id) {
//        List<CustomerDebtImpl> customerDebtImpl = customerService.findCustomerDebtsByCustomerId(id);
//        return new ResponseEntity<>(customerDebtImpl, HttpStatus.OK);
//    }
//}
//
