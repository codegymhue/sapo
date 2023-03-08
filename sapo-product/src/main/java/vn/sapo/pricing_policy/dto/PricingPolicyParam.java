package vn.sapo.pricing_policy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PricingPolicyParam {

    private String title;

    private String pricingPolicyCode;

    private String pricingPolicyType;
}
