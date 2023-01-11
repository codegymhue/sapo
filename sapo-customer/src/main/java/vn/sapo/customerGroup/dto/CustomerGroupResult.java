package vn.sapo.customerGroup.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerGroupResult {
    private Integer id;

    private String title;

    private String cusGrpCode;

    private Integer pricingPolicyId;

    private CusGrpPricingPolicyResult pricingPolicy;

    private String paymentMethodId;

    private Instant createdAt;

    private Long countCus;

    private String description;

    private Integer discount;

}
