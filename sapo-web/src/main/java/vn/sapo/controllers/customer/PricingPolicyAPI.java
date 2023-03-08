package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.pricing_policy.PricingPolicyService;
import vn.sapo.pricing_policy.dto.PricingPolicyParam;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;

@RestController
@RequestMapping("/api/pricingPolicys")
public class PricingPolicyAPI {
    @Autowired
    private PricingPolicyService pricingPolicyService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(pricingPolicyService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody PricingPolicyParam pricingPolicyParam) {

        PricingPolicyResult dto =  pricingPolicyService.create(pricingPolicyParam);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
