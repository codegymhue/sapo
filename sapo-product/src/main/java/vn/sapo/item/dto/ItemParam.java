package vn.sapo.item.dto;

import lombok.*;
import lombok.experimental.*;

import java.math.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ItemParam {

    private Integer id;

    private Integer productId;

    private Integer employeeId;

    private Integer supplierId;

    private Integer purchaseOrderId;

    private Integer quantity;

    private BigDecimal price;

    private String sku;

    private Float discount;

    private int available;

}
