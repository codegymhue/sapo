package vn.sapo.customerGroup.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CreateCusGroupParam {

    @NotBlank(message = "{customer_group.validation..title.notBlank}")
    private String title;
    @NullOrNotBlank
    private String cusGrpCode;

    private Integer defaultPricingPolicyId;
    @Min(value = 0, message = "{customer_group.validation.discount.min}")
    @Max(value = 100, message = "{customer_group.validation.discount.max}")
    private Integer defaultDiscountRate;
    @NullOrNotBlank
    private String defaultPaymentMethodId;

    @Max(value = 255, message = "{customer_group.validation.note.max}")
    private String note;
}
