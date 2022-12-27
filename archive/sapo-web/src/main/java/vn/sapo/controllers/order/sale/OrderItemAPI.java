package vn.sapo.controllers.order.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sapo.order.sale.dto.SaleOrderItemResult;
import vn.sapo.order.sale.item.OrderItemServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemAPI {

    @Autowired
    private OrderItemServiceImpl orderItemService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getAllOrderItemByOrderId(@PathVariable Integer orderId) {
        List<SaleOrderItemResult> orderItemResults = orderItemService.findAllOrderItemByOrderId(orderId);
        return new ResponseEntity<>(orderItemResults,HttpStatus.OK);
    }



}
