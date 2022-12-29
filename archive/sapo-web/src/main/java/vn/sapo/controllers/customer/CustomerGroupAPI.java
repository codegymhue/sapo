package vn.sapo.controllers.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sapo.customerGroup.CustomerGroupMapper;
import vn.sapo.customerGroup.CustomerGroupRepository;
import vn.sapo.customerGroup.CustomerGroupService;

@RestController
@RequestMapping("/api/customer_groups")
public class CustomerGroupAPI {
    @Autowired
    CustomerGroupService customerGroupService;
    @Autowired
    CustomerGroupRepository customerGroupRepository;

    @Autowired
    CustomerGroupMapper customerGroupMapper;

    @GetMapping("/customerGroup")
    public ResponseEntity<?> getAllCustomerGroup(){
        return new ResponseEntity<>(customerGroupService.findAll(), HttpStatus.OK);
    }

}
