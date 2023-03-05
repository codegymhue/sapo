package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.customer.dto.CustomerDataTable;
import vn.sapo.customer.dto.CustomerFilter;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customerGroup.CustomerGroupMapper;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupDataTable;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.UpdateCustomerGroupParam;


@RestController
@RequestMapping("/api/customer_groups")
@CrossOrigin("*")
public class CustomerGroupAPI {
    @Autowired
    CustomerGroupService customerGroupService;

    @Autowired
    CustomerGroupMapper customerGroupMapper;

    @GetMapping
    public ResponseEntity<?> getAllCustomerGroup() {
        return new ResponseEntity<>(customerGroupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sortGroup")
    public ResponseEntity<?> sortByGroup() {
        return new ResponseEntity<>(customerGroupService.sortByGroup(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerGroupResult customerGroupResult = customerGroupService.findById(id);
        return new ResponseEntity<>(customerGroupResult, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody CustomerFilter customerFilter,
                                        @RequestParam(name = "sort", required = false, defaultValue = "ASC" ) String sort
    ) {

        CustomerGroupDataTable customerGroupDataTable = new CustomerGroupDataTable()
//                .setDraw()
//                .setRecordsTotal()
//                .setRecordsFiltered()
//                .setData()
                ;


        // start = 10; length = 5;
//        int start = customerFilter.getStart();
//        int length = customerFilter.getLength();
//
//
//        int page = start / length + 1;
//        Sort sortable = null;
//        if (sort.equals("ASC")) {
//            sortable = Sort.by("id").ascending();
//        }
//        if (sort.equals("DESC")) {
//            sortable = Sort.by("id").descending();
//        }
//
//        Pageable pageable = PageRequest.of(page - 1, length, sortable);
//        Page<CustomerResult> pageableCustomers = customerService.findAllByFilters(customerFilter, pageable);
//
//        CustomerDataTable customerDataTable = new CustomerDataTable();
//
//        if (pageableCustomers != null) {
//            customerDataTable.setRecordsTotal(pageableCustomers.getTotalElements());
//            customerDataTable.setRecordsFiltered(pageableCustomers.getTotalElements());
//            customerDataTable.setData(pageableCustomers.getContent());
//            customerDataTable.setDraw(customerFilter.getDraw());
//        }

        return new ResponseEntity<>(customerGroupDataTable, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCusGroupParam createCusGroupParam) {
        System.out.println(createCusGroupParam);
        CustomerGroupResult dto = customerGroupService.create(createCusGroupParam);
        dto = customerGroupService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCusGroup(@RequestBody UpdateCustomerGroupParam updateCustomerGroupParam) {
        CustomerGroupResult dto = customerGroupService.update(updateCustomerGroupParam);
        dto = customerGroupService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCusGroupById(@PathVariable Integer id) {
        customerGroupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
