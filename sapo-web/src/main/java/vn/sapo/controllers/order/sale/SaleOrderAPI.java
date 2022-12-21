package vn.sapo.controllers.order.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.order.sale.SaleOrderService;
import vn.sapo.order.sale.dto.CreateSaleOrderParam;
import vn.sapo.order.sale.dto.SaleOrderResult;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class SaleOrderAPI {

    @Autowired
    private SaleOrderService orderService;


    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<SaleOrderResult> orderResults = orderService.findAll();

        return new ResponseEntity<>(orderResults, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateSaleOrderParam orderParam) {
        return new ResponseEntity<>(orderService.create(orderParam), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findById(@PathVariable Integer orderId) {
        SaleOrderResult orderResult = orderService.findById(orderId);
        return new ResponseEntity<>(orderResult, HttpStatus.OK);
    }

}
