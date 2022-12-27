package vn.sapo.order.sale.dto;

import lombok.*;
import lombok.experimental.Accessors;
import vn.sapo.order.shared.OrderProductResult;

import java.math.BigDecimal;

@Setter
@Getter
@Accessors(chain = true)
public class SaleOrderItemResult {
    private Integer id;
    private Integer orderId;
    private BigDecimal price;
    private Integer quantity;
    private Float tax;
    private BigDecimal discount;
    private OrderProductResult product;
}
