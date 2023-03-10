package vn.sapo.customerGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CreateCusGroupParam {

    @NotBlank(message = "title khoong trong")
    private String title;

    @NullOrNotBlank
    private String cusGrpCode;

    @NullOrNotBlank
    private String description;

    private Integer pricingPolicyId;

    private Integer discount;

    @NullOrNotBlank
    private String paymentMethodId;

}
