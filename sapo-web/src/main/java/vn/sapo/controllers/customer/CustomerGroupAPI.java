package vn.sapo.controllers.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.customerGroup.CustomerGroupMapper;
import vn.sapo.customerGroup.CustomerGroupRepository;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.UpdateCusGroupParam;

import java.util.List;


@RestController
@RequestMapping("/api/customer_groups")
public class CustomerGroupAPI {
    @Autowired
    CustomerGroupService customerGroupService;

    @Autowired
    CustomerGroupRepository customerGroupRepository;

    @Autowired
    CustomerGroupMapper customerGroupMapper;

    @GetMapping("")
    public ResponseEntity<?> getAllCustomerGroup(){
        return new ResponseEntity<>(customerGroupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sortGroup")
    public ResponseEntity<?> sortByGroup (){
        List<CustomerGroupResult> list = customerGroupService.sortByGroup();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerGroupResult customerGroupResult = customerGroupService.findById(id);
        return new ResponseEntity<>(customerGroupResult, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCusGroupParam createCusGroupParam){
        CustomerGroupResult dto = customerGroupService.create(createCusGroupParam);
        dto = customerGroupService.findById(dto.getId());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCusGroup(@RequestBody UpdateCusGroupParam updateCusGroupParam){
        return new ResponseEntity<>(customerGroupService.update(updateCusGroupParam),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCusGroupById(@PathVariable Integer id) {
        customerGroupService.deleteById(id);
    }


}