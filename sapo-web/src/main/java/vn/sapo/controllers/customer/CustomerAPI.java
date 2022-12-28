package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.CustomerMapper;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.exceptions.NotFoundException;
import vn.sapo.order.sale.SaleOrderService;
import vn.sapo.order.sale.item.OrderItemService;
import vn.sapo.payment.sale.PaymentSaleOrderService;

import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    PaymentSaleOrderService paymentSaleOrderService;

    @Autowired
    SaleOrderService saleOrderService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        List<CustomerResult> customers = customerService.findAll();
        customers.forEach(customer -> {
            BigDecimal spendTotal = getSpendTotalByCustomerId(customer.getId());
            BigDecimal paidTotal = getPaidTotalByCustomerId(customer.getId());
            Integer quantityProductOrder = getQuantityProductOrderByCustomerId(customer.getId());
            Integer quantityItemOrder = getQuantityItemCustomerOrderById(customer.getId());
            Instant lastDayOrder = getLastDayOrderByCustomerId(customer.getId());
            customer.setSpendTotal(spendTotal);
            customer.setDebtTotal(spendTotal.subtract(paidTotal));
            customer.setQuantityProductOrder(quantityProductOrder);
            customer.setQuantityItemOrder(quantityItemOrder);
            customer.setLastDayOrder(lastDayOrder);
        });
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    public BigDecimal getSpendTotalByCustomerId(Integer customerId) {
        BigDecimal spendTotal = saleOrderService.getSpendTotalByCustomerId(customerId);
        if (spendTotal == null)
            spendTotal = BigDecimal.valueOf(0);
        return spendTotal;
    }

    public BigDecimal getPaidTotalByCustomerId(Integer customerId) {
        BigDecimal paidTotal = paymentSaleOrderService.getPaidTotalByCustomerId(customerId);
        if (paidTotal == null)
            paidTotal = BigDecimal.valueOf(0);
        return paidTotal;
    }

    public Integer getQuantityProductOrderByCustomerId(Integer customerId) {
        Integer quantityProductOrder = saleOrderService.getQuantityProductOrder(customerId);
        if (quantityProductOrder == null)
            quantityProductOrder = 0;
        return quantityProductOrder;
    }

    public Integer getQuantityItemCustomerOrderById(Integer customerId) {
        Integer quantityItemOrder = orderItemService.getQuantityItemCustomerOrderById(customerId);
        if (quantityItemOrder == null)
            quantityItemOrder = 0;
        return quantityItemOrder;

    }

    public Instant getLastDayOrderByCustomerId(Integer customerId) {
        Instant lastDayOrder = saleOrderService.getLastDayOrderByCustomerId(customerId);
        return lastDayOrder;
    }

//    @GetMapping("/findAllByStatus")
//    public ResponseEntity<?> findAllByStatus() {
//        List<CustomerResult> customers = customerService.findCustomerByStatus();
//        return new ResponseEntity<>(customers, HttpStatus.OK);


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerResult dto = customerService.findById(id);
        BigDecimal paidTotal = getPaidTotalByCustomerId(dto.getId());
        BigDecimal spendTotal =getSpendTotalByCustomerId(dto.getId());
        Integer  quantityProductOrder = getQuantityProductOrderByCustomerId(dto.getId());
        Integer quantityItemOrder = getQuantityItemCustomerOrderById(dto.getId());
        Instant lastDayOrder = getLastDayOrderByCustomerId(dto.getId());
        dto.setDebtTotal(spendTotal.subtract(paidTotal));
        dto.setSpendTotal(spendTotal);
        dto.setQuantityProductOrder(quantityProductOrder);
        dto.setQuantityItemOrder(quantityItemOrder);
        dto.setLastDayOrder(lastDayOrder);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


//@GetMapping("/page")
//public ResponseEntity<?> getAllCustomerPage(@RequestParam HashMap<String, String> hashMap) {
//    return new ResponseEntity<>(customerService.getAllCustomerItemPage(
//            Integer.valueOf(hashMap.get("pageNo")),
//            Integer.valueOf(hashMap.get("pageSize")),
//            hashMap.get("code"),
//            hashMap.get("name"),
//            hashMap.get("phoneNumber"),
//            hashMap.get("group"),
//            BigDecimal.valueOf(Long.parseLong(hashMap.get("debtsTotal"))),
//
//
//
//
//    );


    //}
    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Integer id) {
        customerService.deleteById(id);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCustomerParam createCustomerParam) {
        CustomerResult dto = customerService.create(createCustomerParam);
        CreateAddressParam createAddressParam = createCustomerParam.getCreateAddressParam();
        if (createAddressParam == null)
            return new ResponseEntity<>(dto, HttpStatus.OK);
        createAddressParam.setCustomerId(dto.getId());
        addressService.create(createAddressParam);
        dto = customerService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerParam updateCustomer) {
        return new ResponseEntity<>(customerService.update(updateCustomer), HttpStatus.OK);
    }





    @GetMapping("/customerGroup")
    public ResponseEntity<?> getAllCustomerGroup() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/updateStatusAvailable")
    public ResponseEntity<?> updateStatusAvailable(@RequestBody List<Integer> arrayIdCustomer) {
        customerService.changeStatusToAvailable(arrayIdCustomer, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateStatusUnavailable")
    public ResponseEntity<?> updateStatusUnavailable(@RequestBody List<Integer> arrayIdCustomer) {
        customerService.changeStatusToAvailable(arrayIdCustomer, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping("/customerGroup")
//    public ResponseEntity<?> getAllCustomerGroup(){
//      return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK);
//    }



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

