package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.customerGroup.CustomerGroupMapper;
import vn.sapo.customerGroup.CustomerGroupService;
<<<<<<< HEAD
import vn.sapo.customerGroup.dto.*;
=======
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.UpdateCusGroupParam;
import vn.sapo.customerGroup.dto.UpdateCustomerGroupParam;

>>>>>>> cf9e56958713b8ee99a298cabdf1ddcf9488ab60

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
    public ResponseEntity<?> filter(@RequestBody CustomerGroupFilter customerGroupFilter,
                                        @RequestParam(name = "sort", required = false, defaultValue = "ASC" ) String sort
    ) {

        int start = customerGroupFilter.getStart();
        int length = customerGroupFilter.getLength();

        int page = start / length + 1;

        Sort sortable = null;

        if (sort.equals("ASC")) {
            sortable = Sort.by("title").ascending();
        }

        if (sort.equals("DESC")) {
            sortable = Sort.by("title").descending();
        }

        Pageable pageable = PageRequest.of(page - 1, length, sortable);

        Page<CustomerGroupResult> pageableCustomerGroups = customerGroupService.findAllByFilters(customerGroupFilter, pageable);

        CustomerGroupDataTable customerGroupDataTable = new CustomerGroupDataTable()
                .setDraw(customerGroupFilter.getDraw())
                .setRecordsTotal(pageableCustomerGroups.getTotalElements())
                .setRecordsFiltered(pageableCustomerGroups.getTotalElements())
                .setData(pageableCustomerGroups.getContent())
                ;

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
    public ResponseEntity<?> updateCusGroup(@RequestBody UpdateCusGroupParam updateCusGroupParam) {
        CustomerGroupResult dto = customerGroupService.update(updateCusGroupParam);
        dto = customerGroupService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCusGroupById(@PathVariable Integer id) {
        customerGroupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
