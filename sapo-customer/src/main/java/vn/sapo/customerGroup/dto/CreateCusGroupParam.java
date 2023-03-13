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

    @NotBlank(message = "{customer.validation.CreateCusGroupParam.title.notBlank}")
    private String title;

    private String cusGrpCode;

    private String description;

    private Integer pricingPolicyId;

    @Min(value = 0, message = "{customer.validation.CreateCusGroupParam.discount.min}")
    @Max(value = 100, message = "{customer.validation.CreateCusGroupParam.discount.max}")
    private Integer discount;

    private String paymentMethodId;

}
