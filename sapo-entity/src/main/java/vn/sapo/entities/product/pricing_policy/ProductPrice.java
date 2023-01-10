package vn.sapo.entities.product.pricing_policy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class ProductPrice {
    private Integer id;
    private String pricePolicyTitle;
    private BigDecimal price;
}
