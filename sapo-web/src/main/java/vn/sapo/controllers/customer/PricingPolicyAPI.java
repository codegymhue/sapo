//package vn.sapo.controllers.customer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import vn.sapo.pricing_policy.PricingPolicyService;
//import vn.sapo.pricing_policy.dto.PricingPolicyParam;
//import vn.sapo.pricing_policy.dto.PricingPolicyResult;
//import vn.sapo.shared.controllers.BaseController;
//
//@RestController
//@RequestMapping("/api/pricing-policies")
//public class PricingPolicyAPI extends BaseController {
//    @Autowired
//    private PricingPolicyService pricingPolicyService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable Integer id) {
//        PricingPolicyResult dto = pricingPolicyService.findById(id);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//    @GetMapping
//    public ResponseEntity<?> findAll() {
//        return new ResponseEntity<>(pricingPolicyService.findAll(), HttpStatus.OK);
//    }
//
//    @PostMapping
//    private ResponseEntity<?> create(@RequestBody @Validated PricingPolicyParam pricingPolicyParam) {
//        PricingPolicyResult dto = pricingPolicyService.create(pricingPolicyParam);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }
//}
