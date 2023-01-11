package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.*;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.excel.ExcelHelper;
import vn.sapo.excel.ExcelService;
import vn.sapo.excel.ResponseMessage;
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
    ExcelService excelService;
    @Autowired
    CustomerGroupService customerGroupService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<CustomerResult> customers = customerService.findAll();
        customers.forEach(this::setData);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerResult dto = customerService.findById(id);
        setData(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping("/filter")
    public ResponseEntity<?> testFilter(@RequestBody CustomerFilter customerFilter,
                                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                        @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {

        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }


        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<CustomerResult> pagealeCustomers = customerService.findAllByFilters(customerFilter, pageable);

        CustomerDataTable customerDataTable = new CustomerDataTable();
        if(pagealeCustomers != null){
            customerDataTable.setDraw(pagealeCustomers.getPageable().getPageNumber());
            customerDataTable.setRecordsTotal(pagealeCustomers.getSize());
            customerDataTable.setRecordsFiltered(pagealeCustomers.getSize());
            customerDataTable.setContent(pagealeCustomers.getContent());
        }
        return new ResponseEntity<>(pagealeCustomers, HttpStatus.OK);

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


//    @DeleteMapping("/delete")
//    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
//        customerService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


//
//    @DeleteMapping("/delete")
//    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
//        customerService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }



    @PutMapping("/updateStatusAvailable")
    public ResponseEntity<?> updateStatusAvailable(@RequestBody List<Integer> arrayIdCustomer) {
        customerService.changeStatusToAvailable(arrayIdCustomer, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // UpLoad File Excel
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<CreateCustomerParam> customers = excelService.save(file);
                customers.forEach(createCustomerParam1 -> create(createCustomerParam1));
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @PutMapping("/updateStatusUnavailable")

    public ResponseEntity<?> updateStatusUnavailable(@RequestBody List<Integer> arrayIdCustomer) {
        customerService.changeStatusToAvailable(arrayIdCustomer, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    findAllCustomerByGroupAndStatus


    public void setData(CustomerResult customer) {
        BigDecimal spendTotal = getSpendTotalByCustomerId(customer.getId());
        BigDecimal paidTotal = getPaidTotalByCustomerId(customer.getId());
        customer.setSpendTotal(spendTotal);
        customer.setDebtTotal(spendTotal.subtract(paidTotal));
        customer.setQuantityProductOrder(getQuantityProductOrderByCustomerId(customer.getId()));
        customer.setQuantityItemOrder(getQuantityItemCustomerOrderById(customer.getId()));
        customer.setLastDayOrder(getLastDayOrderByCustomerId(customer.getId()));
//        Integer pricingPolicy = getPricingPolicyByCustomerId(customer.getId());

    }
//public Integer getPricingPolicyByCustomerId(Integer customerId){
//        Integer pricingPolicy = customerGroupService.getPricingPolicyByCustomerId(customerId);
//}
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

    @PostMapping("/findAllCustomerByGroup")
    public ResponseEntity<?> findAllByGroupId(@RequestBody List<Integer> arrGroupId) {
        List<CustomerResult> customers = customerService.findAllByGroupListId(arrGroupId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}

