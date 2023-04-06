package vn.sapo.controllers.pricing_policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.sapo.mail.EMailSender;
import vn.sapo.pricing_policy.PricingPolicyService;
import vn.sapo.pricing_policy.dto.PricingPolicyParam;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;

import java.util.Set;

@RestController
@RequestMapping("/api/pricing-policies")
public class PricingPolicyAPI extends Thread {
//    @Autowired
//    private EMailSender eMailSender;
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
    public ResponseEntity<?> findByTitles(Set<String> titles){
        return new ResponseEntity<>(pricingPolicyService.findByTitles(titles), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Validated PricingPolicyParam param) {
        PricingPolicyResult result = pricingPolicyService.create(param);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
