package vn.sapo.pricing_policy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
