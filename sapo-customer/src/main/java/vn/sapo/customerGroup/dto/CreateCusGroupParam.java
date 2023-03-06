package vn.sapo.customerGroup.dto;

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
public class CreateCusGroupParam {
//    private Integer id;

    private String title;

    private String cusGrpCode;

    private String description;

    private Integer pricingPolicyId;

    private Integer discount;

    private String paymentMethodId;

}
