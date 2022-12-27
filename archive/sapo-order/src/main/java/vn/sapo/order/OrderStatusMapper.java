package vn.sapo.order;

import org.springframework.stereotype.Component;
import vn.sapo.entities.order.OrderStatus;
import vn.sapo.order.sale.dto.SaleOrderStatusResult;

@Component
public class OrderStatusMapper {
    public SaleOrderStatusResult toDTO(OrderStatus orderStatus) {
        return new SaleOrderStatusResult()
                .setName(orderStatus.getName());
    }
}
