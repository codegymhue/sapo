package vn.sapo.customerGroup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateCustomerGroupParam {
    @NotNull
    private Integer id;

    @NotBlank
    private String title;
    @NullOrNotBlank
    private String cusGrpCode;

    private Integer pricingPolicyId;

    @NullOrNotBlank
    private String paymentMethodId;
    @NullOrNotBlank
    private String description;

    private Integer discount;
}
