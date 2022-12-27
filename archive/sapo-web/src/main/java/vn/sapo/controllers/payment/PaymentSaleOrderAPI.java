package vn.sapo.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vn.sapo.payment.sale.PaymentSaleOrderService;
import vn.sapo.payment.sale.dto.CreatePaymentSaleOrderParam;

@RestController
@RequestMapping("/api/payment/paymentOrder")
public class PaymentSaleOrderAPI {
    @Autowired
    private PaymentSaleOrderService paymentSaleOrderService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(paymentSaleOrderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPaymentOrder(@RequestBody CreatePaymentSaleOrderParam paymentOrderParam) {

        return new ResponseEntity<>(paymentSaleOrderService.create(paymentOrderParam), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAllByOrderId(@PathVariable Integer orderId) {
        return new ResponseEntity<>(paymentSaleOrderService.findAllByOrderId(orderId), HttpStatus.OK);
    }
}

