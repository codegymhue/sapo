package vn.sapo.controllers.customer;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.CustomerRepository;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.order.sale.SaleOrderService;
import vn.sapo.order.sale.item.OrderItemService;
import vn.sapo.payment.sale.PaymentSaleOrderService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
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
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<CustomerResult> customers = customerService.findAll();
        customers.forEach(this::setData);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerResult dto = customerService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable Integer id) {
        addressService.deleteByCustomerId(id);
        customerService.deleteById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCustomerParam createCustomerParam) {
        System.out.println(createCustomerParam);
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

//    @GetMapping("/customerGroup")
//    public ResponseEntity<?> getAllCustomerGroup() {
//        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

    public void setData(CustomerResult customer) {
        BigDecimal spendTotal = getSpendTotalByCustomerId(customer.getId());
        BigDecimal paidTotal = getPaidTotalByCustomerId(customer.getId());
        customer.setSpendTotal(spendTotal);
        customer.setDebtTotal(spendTotal.subtract(paidTotal));
        customer.setQuantityProductOrder(getQuantityProductOrderByCustomerId(customer.getId()));
        customer.setQuantityItemOrder(getQuantityItemCustomerOrderById(customer.getId()));
        customer.setLastDayOrder(getLastDayOrderByCustomerId(customer.getId()));
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
        return saleOrderService.getLastDayOrderByCustomerId(customerId);
    }
    @PostMapping ("/findAllCustomerByGroup")
    public ResponseEntity<?> findAllByGroupId(@RequestBody  List<Integer> arrGroupId ) {
        List<CustomerResult> customers = customerService.findAllByGroupListId(arrGroupId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/findAllCustomerByGender")
    public ResponseEntity<?> findAllByGenderId(@RequestBody String arrGenderId) {
        List<CustomerResult> customers = customerService.findAllByGenderId(arrGenderId);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @PostMapping("/findAllCustomerEmployee")
    public ResponseEntity<?> findAllByEmployeeId(@RequestBody List<Integer> arrEmployeeId) {
        List<CustomerResult> customers = customerService.findAllEmployeeListId(arrEmployeeId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

//    @PostMapping("/findAllCustomerByStatus")
//    public ResponseEntity<?> findAllStatusListId(@RequestBody List<String> arrStatusId) {
//        List<CustomerResult> customers = customerService.findAllByStatusListId(arrStatusId);
//        return new ResponseEntity<>(customers, HttpStatus.OK);
//    }
}

