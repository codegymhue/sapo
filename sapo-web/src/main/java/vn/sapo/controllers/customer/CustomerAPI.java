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
import vn.sapo.customers.AddressService;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.*;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.excel.ExcelHelper;
import vn.sapo.excel.ExcelService;
import vn.sapo.order.sale.SaleOrderService;
import vn.sapo.order.sale.item.OrderItemService;
import vn.sapo.shared.controllers.BaseController;
//import vn.sapo.shared.exceptions.DataInputException;
import vn.sapo.shared.exceptions.ValidationException;
import vn.sapo.voucher.receipt.ReceiptVoucherService;
import vn.sapo.supplier.excel.ResponseMessage;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerAPI extends BaseController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ReceiptVoucherService receiptVoucherService;

    @Autowired
    SaleOrderService saleOrderService;

    @Autowired
    CustomerGroupService customerGroupService;

    @Autowired
    ExcelService excelService;

//   @GetMapping
//   public ResponseEntity<?> findAll() {
//       List<CustomerResult> customers = customerService.findAll();
//       customers.forEach(this::setData);
//       return new ResponseEntity<>(customers, HttpStatus.OK);
//   }

   @GetMapping("/{id}")
   public ResponseEntity<?> findById(@PathVariable Integer id) {
       CustomerResult dto = customerService.findById(id);
//       setData(dto);
       return new ResponseEntity<>(dto, HttpStatus.OK);
   }


    @PostMapping("/filter")
    public ResponseEntity<?> testFilter(@RequestBody CustomerFilter customerFilter,
                                        @RequestParam(name = "sort", required = false, defaultValue = "ASC" ) String sort
    ) {
        // start = 10; length = 5;
        int start = customerFilter.getStart();
        int length = customerFilter.getLength();

        int page = start / length + 1;
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }

        Pageable pageable = PageRequest.of(page - 1, length, sortable);
        Page<CustomerResult> pageableCustomers = customerService.findAllByFilters(customerFilter, pageable);

        CustomerDataTable customerDataTable = new CustomerDataTable();

        if (pageableCustomers != null) {
            customerDataTable.setRecordsTotal(pageableCustomers.getTotalElements());
            customerDataTable.setRecordsFiltered(pageableCustomers.getTotalElements());
            customerDataTable.setData(pageableCustomers.getContent());
            customerDataTable.setDraw(customerFilter.getDraw());
        }
        return new ResponseEntity<>(customerDataTable, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable Integer id) {
        addressService.deleteByCustomerId(id);
        customerService.deleteById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCustomerParam createCustomerParam) {
        CreateAddressParam createAddressParam = createCustomerParam.getCreateAddressParam();
        if (createAddressParam == null)
            throw new ValidationException(new HashMap<>() {{
                put("line1", "Dia chi khong duoc de trong");
            }});
        CustomerResult dto = customerService.create(createCustomerParam);

//        createAddressParam.setCustomerId(dto.getId());
//        addressService.create(createAddressParam);
        dto = customerService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateCustomerParam updateCustomer) {
        return new ResponseEntity<>(customerService.update(updateCustomer), HttpStatus.OK);
    }

    @PutMapping("/updateStatusAvailable")
    public ResponseEntity<?> updateStatusAvailable(@RequestBody List<Integer> customerIds) {
        customerService.changeStatusToAvailable(customerIds, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateStatusUnavailable")
    public ResponseEntity<?> updateStatusUnavailable(@RequestBody List<Integer> arrayIdCustomer) {
        customerService.changeStatusToAvailable(arrayIdCustomer, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // UpLoad File Excel
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<CreateCustomerParam> customers = excelService.save(file);
                customers.forEach(param -> create(param));
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


//   public void setData(CustomerResult customer) {
//       BigDecimal spendTotal = getSpendTotalByCustomerId(customer.getId());
//       BigDecimal paidTotal = getPaidTotalByCustomerId(customer.getId());
//       customer.setSpendTotal(spendTotal);
//       customer.setDebtTotal(spendTotal.subtract(paidTotal));
//       customer.setQuantityProductOrder(getQuantityProductOrderByCustomerId(customer.getId()));
//       customer.setQuantityItemOrder(getQuantityItemCustomerOrderById(customer.getId()));
//       customer.setLastDayOrder(getLastDayOrderByCustomerId(customer.getId()));
//   }
//
//    public BigDecimal getSpendTotalByCustomerId(Integer customerId) {
//        BigDecimal spendTotal = saleOrderService.getSpendTotalByCustomerId(customerId);
//        if (spendTotal == null)
//            spendTotal = BigDecimal.valueOf(0);
//        return spendTotal;
//    }
//
//    public BigDecimal getPaidTotalByCustomerId(Integer customerId) {
//        BigDecimal paidTotal = new BigDecimal(0);//= paymentSaleOrderService.getPaidTotalByCustomerId(customerId);
//        if (paidTotal == null)
//            paidTotal = BigDecimal.valueOf(0);
//        return paidTotal;
//    }
//
//
//    public Integer getQuantityProductOrderByCustomerId(Integer customerId) {
//        Integer quantityProductOrder = saleOrderService.getQuantityProductOrder(customerId);
//        if (quantityProductOrder == null)
//            quantityProductOrder = 0;
//        return quantityProductOrder;
//    }
//
//    public Integer getQuantityItemCustomerOrderById(Integer customerId) {
//        Integer quantityItemOrder = orderItemService.getQuantityItemCustomerOrderById(customerId);
//        if (quantityItemOrder == null)
//            quantityItemOrder = 0;
//        return quantityItemOrder;
//    }


    public Instant getLastDayOrderByCustomerId(Integer customerId) {
        return saleOrderService.getLastDayOrderByCustomerId(customerId);
    }

    @PostMapping("/findAllCustomerByGroup")
    public ResponseEntity<?> findAllByGroupId(@RequestBody List<Integer> arrGroupId) {
        List<CustomerResult> customers = customerService.findAllByGroupListId(arrGroupId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

//    @GetMapping("/{id}/address")
//    public ResponseEntity<?> shippingAddress(@PathVariable Integer id) {
//        CustomerResult dto = customerService.findById(id);
//        setData(dto);
//
//        CustomerResultDataTable customerResultDataTable = new CustomerResultDataTable();
//        List<CustomerResult> customerResults = new ArrayList<>();
//        customerResults.add(dto);
//        customerResultDataTable.setData(customerResults);
//
//        return new ResponseEntity<>(customerResultDataTable, HttpStatus.OK);
//    }


//    public void setData(CustomerResult customer) {
//        BigDecimal spendTotal = getSpendTotalByCustomerId(customer.getId());
//        BigDecimal paidTotal = getPaidTotalByCustomerId(customer.getId());
//
//        customer.setSpendTotal(spendTotal);
//        customer.setDebtTotal(spendTotal.subtract(paidTotal));
//        customer.setQuantityProductOrder(getQuantityProductOrderByCustomerId(customer.getId()));
//        customer.setQuantityItemOrder(getQuantityItemCustomerOrderById(customer.getId()));
//        customer.setLastDayOrder(getLastDayOrderByCustomerId(customer.getId()));
//    }

//    public BigDecimal getSpendTotalByCustomerId(Integer customerId) {
//        BigDecimal spendTotal = saleOrderService.getSpendTotalByCustomerId(customerId);
//
//        if (spendTotal == null)
//            spendTotal = BigDecimal.valueOf(0);
//
//        return spendTotal;
//    }

//    public BigDecimal getPaidTotalByCustomerId(Integer customerId) {
//        BigDecimal paidTotal = paymentSaleOrderService.getPaidTotalByCustomerId(customerId);
//
//        if (paidTotal == null)
//            paidTotal = BigDecimal.valueOf(0);
//
//        return paidTotal;
//    }

//    public Integer getQuantityProductOrderByCustomerId(Integer customerId) {
//        Integer quantityProductOrder = saleOrderService.getQuantityProductOrder(customerId);
//
//        if (quantityProductOrder == null)
//            quantityProductOrder = 0;
//
//        return quantityProductOrder;
//    }

//    public Integer getQuantityItemCustomerOrderById(Integer customerId) {
//        Integer quantityItemOrder = orderItemService.getQuantityItemCustomerOrderById(customerId);
//
//        if (quantityItemOrder == null)
//            quantityItemOrder = 0;
//
//        return quantityItemOrder;
//    }


//    public Instant getLastDayOrderByCustomerId(Integer customerId) {
//        return saleOrderService.getLastDayOrderByCustomerId(customerId);
//    }

}

