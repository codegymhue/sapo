package vn.sapo.customerGroup.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateCusGroupParam {
    private Integer id;

    private String title;

    private String cusGrpCode;

    private Integer pricingPolicyId;

    private String paymentMethodId;

    private String description;

    private Integer discount;
}
