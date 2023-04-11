package vn.sapo.pricing_policy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.entities.product.pricing_policy.PricingPolicyType;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class PricingPolicyParam {

    @NotBlank(message = "{pricing_policy.validation.title.notBlank}")
    @Length(max = 255, message = "{pricing_policy.validation.title.length}")
    private String title;


    @NotBlank(message = "{pricing_policy.validation.pricingPolicyCode.notBlank}")
    @Length(max = 255, message = "{pricing_policy.validation.pricingPolicyCode.length}")
    private String pricingPolicyCode;

    private PricingPolicyType pricingPolicyType;

}
