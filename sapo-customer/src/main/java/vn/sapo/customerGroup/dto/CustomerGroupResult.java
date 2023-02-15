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

    private Integer pricingPolicyId;

    private String paymentMethodId;

    private Instant createdAt;

    private Long countCus;

    private String description;

    private Integer discount;



}
