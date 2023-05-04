package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.sapo.customerGroup.CustomerGroupMapper;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.customerGroup.dto.*;
import vn.sapo.shared.controllers.BaseController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer_groups")
public class CustomerGroupAPI extends BaseController {
    @Autowired
    CustomerGroupService customerGroupService;

    @Autowired
    CustomerGroupMapper customerGroupMapper;

    @GetMapping
    public ResponseEntity<?> getAllCustomerGroup() {
        return new ResponseEntity<>(customerGroupService.findAll(), HttpStatus.OK);
    }
//    @GetMapping("/sortGroup")
//    public ResponseEntity<?> sortByGroup() {
//        return new ResponseEntity<>(customerGroupService.sortByGroup(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerGroupResult customerGroupResult = customerGroupService.findById(id);
        return new ResponseEntity<>(customerGroupResult, HttpStatus.OK);
    }

    @PostMapping("/pagination")
    private ResponseEntity<?> getAllCustomerGroupsPagination(@Valid @RequestBody DataTablesInput input) {

        Page<ICustomerGroupResult> pageableCustomerGroups = customerGroupService.getAllCustomerGroupsPagination(input);

        DataTablesOutput<ICustomerGroupResult> output = new DataTablesOutput<ICustomerGroupResult>()
                .setDraw(input.getDraw())
                .setRecordsTotal(pageableCustomerGroups.getTotalElements())
                .setRecordsFiltered(pageableCustomerGroups.getTotalElements())
                .setData(pageableCustomerGroups.getContent());

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/{id}/customer")
    private ResponseEntity<?> findCustomerGroupByCustomer(@PathVariable Integer id) {

        CustomerGroupResult customerGroupResult =
                customerGroupService.findCustomerGroupByCustomerId(id);

        return new ResponseEntity<>(customerGroupResult, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateCusGroupParam createCusGroupParam) {
        CustomerGroupResult dto = customerGroupService.create(createCusGroupParam);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCusGroup(@PathVariable Integer id,
                                            @RequestBody @Validated UpdateCusGroupParam updateCusGroupParam
    ) {
        CustomerGroupResult dto = customerGroupService.update(id, updateCusGroupParam);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCusGroupById(@PathVariable Integer id) {
        customerGroupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
