package vn.sapo.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sapo.payment_method.PaymentMethodService;

import java.util.Set;

@RestController
@RequestMapping("/api/payment_methods")
public class PaymentMethodAPI {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(paymentMethodService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/histories")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(paymentMethodService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByTitles")
    public ResponseEntity<?> findByTitles(Set<String> titles) {
        return new ResponseEntity<>(paymentMethodService.findByTitles(titles), HttpStatus.OK);
    }

}