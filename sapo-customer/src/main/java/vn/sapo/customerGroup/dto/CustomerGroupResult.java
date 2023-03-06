package vn.sapo.customerGroup.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerGroupResult {

    private Integer id;

    private String title;

    private String cusGrpCode;

    private String customerGroupType;

    private String description;

    private Long countCus;

    private Instant createdAt;

    //    private String paymentMethodId;
//
//    private Integer pricingPolicyId;

//    private Integer discount;

}
