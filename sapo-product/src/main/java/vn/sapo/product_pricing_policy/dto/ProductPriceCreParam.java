package vn.sapo.product_pricing_policy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ProductPriceCreParam {
    private Integer pricingPolicyId;
    private BigDecimal price;
}
