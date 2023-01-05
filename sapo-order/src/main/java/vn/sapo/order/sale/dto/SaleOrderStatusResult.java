package vn.sapo.order.sale.dto;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class SaleOrderStatusResult {
    private String title;

}
