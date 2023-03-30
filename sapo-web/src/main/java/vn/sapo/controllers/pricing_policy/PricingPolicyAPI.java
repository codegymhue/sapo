package vn.sapo.controllers.pricing_policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sapo.pricing_policy.PricingPolicyService;

import java.util.Set;

@RestController
@RequestMapping("/api/pricing_policies")
public class PricingPolicyAPI {
    @Autowired
    private PricingPolicyService pricingPolicyService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(pricingPolicyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(pricingPolicyService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByTitles")
    public ResponseEntity<?> findByTitles(Set<String> titles) {
        return new ResponseEntity<>(pricingPolicyService.findByTitles(titles), HttpStatus.OK);
    }
}
