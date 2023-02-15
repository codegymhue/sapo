package vn.sapo.pricing_policy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PricingPolicyResult {
    private Integer id;

    private String pricingPolicyCode;

    private String title;

    @Override
    public String toString() {
        return  title;
    }

}
