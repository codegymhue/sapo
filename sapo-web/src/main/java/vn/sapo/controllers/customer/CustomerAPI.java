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



    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Integer id) {
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

        @GetMapping("/findAllCustomerByGroupAndStatus/{groupId},{status}")
        public ResponseEntity<?> findAllCustomerByGroupAndStatus(@PathVariable Integer groupId, @PathVariable String status) {
            List<CustomerResult> customers = customerService.findAllCustomerByGroupAndStatus(groupId, status);
        customers.forEach(this::setData);
        return new ResponseEntity<>(customers, HttpStatus.OK);
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
}

