package vn.sapo.order.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PurchaseOrderItemResult {

    private Integer id;

    private Integer productId;

    private Integer itemId;

    private Integer purchaseOrderId;

    private BigDecimal price;

    private Integer quantity;

}
