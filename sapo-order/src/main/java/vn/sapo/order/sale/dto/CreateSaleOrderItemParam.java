package vn.sapo.order.sale.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CreateSaleOrderItemParam {

    private BigDecimal price;

    private Integer quantity;

    private Integer productId;

    private BigDecimal discount;

    private Float tax;
}
