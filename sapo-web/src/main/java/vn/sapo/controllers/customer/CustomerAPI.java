package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.contact.ContactCustomerService;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.*;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.customerGroup.dto.DataTablesInput;
import vn.sapo.customerGroup.dto.DataTablesOutput;
import vn.sapo.customers.AddressService;
import vn.sapo.excel.ExcelHelper;
import vn.sapo.order.sale.SaleOrderService;
import vn.sapo.order.sale.item.OrderItemService;
import vn.sapo.shared.controllers.BaseController;
import vn.sapo.supplier.excel.ResponseMessage;
import vn.sapo.voucher.receipt.ReceiptVoucherService;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerAPI extends BaseController{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ContactCustomerService contactCustomerService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ReceiptVoucherService receiptVoucherService;

    @Autowired
    SaleOrderService saleOrderService;

    @Autowired
    CustomerGroupService customerGroupService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CreateCustomerThread createCustomerThread;

//    @Autowired
//    ExcelService excelService;

//   @GetMapping
//   public ResponseEntity<?> findAll() {
//       List<CustomerResult> customers = customerService.findAll();
//       customers.forEach(this::setData);
//       return new ResponseEntity<>(customers, HttpStatus.OK);
//   }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerResult dto = customerService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping("/filter")
    public ResponseEntity<?> testFilter(@RequestBody CustomerFilter customerFilter,
                                        @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Integer id) {
        return new ResponseEntity<>(customerService.deleteById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateCustomerParam createCustomerParam) {
        CustomerResult dto = customerService.create(createCustomerParam);

        dto = customerService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PostMapping("/upload")
    public ResponseEntity<?> createCustomerByExcel(@RequestBody @Valid CreateSeriesCustomerParam createSeriesCustomerParam){
        createCustomerThread.start(createSeriesCustomerParam);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody @Validated UpdateCustomerParam updateCustomer) {
        customerService.update(id, updateCustomer);

        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateSeriesCustomer(@RequestBody CustomerUpdateSeries customerUpdateSeries) {
        return new ResponseEntity<>(customerService.updateSeries(customerUpdateSeries), HttpStatus.OK);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<?> updateStatusAvailable(@RequestBody CustomerUpdateStatus customerUpdateStatus) {
        int customerId = customerUpdateStatus.getCustomerId();
        boolean status = customerUpdateStatus.getStatus();
        CustomerResult customerResult = customerService.changeStatusToAvailable(customerId, status);
        return new ResponseEntity<>(customerResult, HttpStatus.OK);
    }

    // UpLoad File Excel
//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//        if (ExcelHelper.hasExcelFormat(file)) {
//            try {
////                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(customerService.excelToCustomerCreate(file));
//            } catch (Exception e) {
//                e.printStackTrace();
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//            }
//        }
//
//        message = "Please upload an excel file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//    }

    @PostMapping("/{id}/contacts")
    private ResponseEntity<?> createContact(@PathVariable Integer id,
                                            @RequestBody @Validated CreateContactParam contactParam) {
        ContactResult dto = contactCustomerService.createByCustomerId(id, contactParam);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/contacts/pagination")
    private ResponseEntity<?> getContactsPagination(@PathVariable Integer id,
                                                    @RequestBody @Validated DataTablesInput input) {
        int draw = input.getDraw();
        int start = input.getStart();
        int length = input.getLength();
        int page = start / length + 1;

        Sort s = Sort.by(Sort.Direction.DESC, "id");

        Pageable pageable = PageRequest.of(page - 1, length, s);

        Page<ContactResult> dtoPage =
                contactCustomerService.findAllContact(pageable, id);

        DataTablesOutput<ContactResult> output = new DataTablesOutput<ContactResult>()
                .setDraw(draw)
                .setRecordsTotal(dtoPage.getTotalElements())
                .setRecordsFiltered(dtoPage.getTotalElements())
                .setData(dtoPage.getContent());

        return new ResponseEntity<>(output, HttpStatus.OK);
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

