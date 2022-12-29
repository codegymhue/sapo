package vn.sapo.pricingPolicy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.pricing_policy.PricingPolicyType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class PricingPolicyResult {
    private Integer id;

    private String pricingPolicyCode;

    private String title;

}
