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
@RequestMapping("/api")
public class CustomerGroupAPI {
    @Autowired
    CustomerGroupService customerGroupService;

    @Autowired
    CustomerGroupRepository customerGroupRepository;

    @Autowired
    CustomerGroupMapper customerGroupMapper;

    @GetMapping("/customer_groups")
    public ResponseEntity<?> getAllCustomerGroup(){
        return new ResponseEntity<>(customerGroupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/customer_groups/sortGroup")
    public ResponseEntity<?> sortByGroup (){
        List<CustomerGroupResult> list = customerGroupService.sortByGroup();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/customer_groups/pricingPolicy ")
    public ResponseEntity<?> getAllPricingPolicy (){
        List<CustomerGroupResult> list = customerGroupService.getAllPricingPolicy();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/customer_groups/PaymentMethod ")
    public ResponseEntity<?> getAllPaymentMethod(){
        List<CustomerGroupResult> list = customerGroupService.getAllPaymentMethod();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/customer_groups/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CustomerGroupResult customerGroupResult = customerGroupService.findById(id);
        return new ResponseEntity<>(customerGroupResult, HttpStatus.OK);
    }

    @PostMapping("/customer_groups/create")
    public ResponseEntity<?> create(@RequestBody CreateCusGroupParam createCusGroupParam){
        CustomerGroupResult dto = customerGroupService.create(createCusGroupParam);
        dto = customerGroupService.findById(dto.getId());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/customer_groups/update")
    public ResponseEntity<?> updateCusGroup(@RequestBody UpdateCusGroupParam updateCusGroupParam){
        return new ResponseEntity<>(customerGroupService.update(updateCusGroupParam),HttpStatus.OK);
    }

    @DeleteMapping("/customer_groups/delete/{id}")
    public void deleteCusGroupById(@PathVariable Integer id) {
        customerGroupService.deleteById(id);
    }


}
