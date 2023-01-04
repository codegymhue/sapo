package vn.sapo.item.dto;

import lombok.*;
import lombok.experimental.*;
import vn.sapo.product.dto.*;

import java.math.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ItemResult {

    private Integer id;

    private Integer productId;

    private Integer employeeId;

//    private EmployeeResult employee;

    private Integer supplierId;

    private Integer purchaseOrderId;

    private Integer quantity;

    private BigDecimal price;

    private Integer available;

    private Integer trading;

    private ProductResult productResult;

}
