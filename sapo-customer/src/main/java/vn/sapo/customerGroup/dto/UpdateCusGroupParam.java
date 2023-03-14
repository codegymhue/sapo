package vn.sapo.customerGroup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateCusGroupParam {

    @NotBlank(message = "{customer_group.validation.title.notBlank}")
    private String title;

    @Length(max = 50, message = "{customer_group.validation.cusGrpCode.length}")
    private String cusGrpCode;

    private Integer defaultPricingPolicyId;

    @NullOrNotBlank
    private String defaultPaymentMethodId;

    @Length(max = 255, message = "{customer_group.validation.note.max}")
    private String note;

    @Min(value = 0, message = "{customer_group.validation.defaultDiscountRate.min}")
    @Max(value = 100, message = "{customer_group.validation.defaultDiscountRate.max}")
    private Integer defaultDiscountRate;
}
