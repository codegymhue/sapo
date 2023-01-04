package vn.sapo.order.sale.dto;


import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CreateSaleOrderParam {

    private String fullName;

    private String mobile;

    private String line1;

    private String line2;

    private String city;

    private String province;

    private String zipCode;

    private Integer customerId;

    private String orderCode;

    private String description;

    private BigDecimal discount;

    List<CreateSaleOrderItemParam> saleOrderItems;

}
