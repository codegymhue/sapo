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
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api/customer_groups")
@CrossOrigin("*")
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
    private ResponseEntity<?> test(@Valid @RequestBody DataTablesInput input) {
        int draw = input.getDraw();
        int start = input.getStart();
        int length = input.getLength();
        int page = start / length + 1;

        Object sort = input.getOrder().get(0);
        LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) sort;
        String order = linkedHashMap.get("dir").toUpperCase();

        Sort s = Sort.by(Sort.Direction.valueOf(order), "title");

        Pageable pageable = PageRequest.of(page - 1, length, s);

        Page<ICustomerGroupResult> pageableCustomerGroups =
                customerGroupService.findAllCustomerGroupPageable(pageable);

        DataTablesOutput<ICustomerGroupResult> output = new DataTablesOutput<>();
        output.setDraw(draw);
        output.setRecordsTotal(pageableCustomerGroups.getTotalElements());
        output.setRecordsFiltered(pageableCustomerGroups.getTotalElements());
        output.setData(pageableCustomerGroups.getContent());

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
