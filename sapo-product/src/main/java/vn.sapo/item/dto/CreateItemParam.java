package vn.sapo.item.dto;

import lombok.*;
import lombok.experimental.*;

import java.math.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CreateItemParam {

    private Integer productId;

    private Integer employeeId;

    private Integer quantity;

    private BigDecimal price;

    private Integer available;

}
