package vn.sapo.customerGroup.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.tax.Tax;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CustomerGroupResult {
    private Integer id;

    private String title;

    private String cusGrpCode;

    private Integer pricingPolicyId;

    private CusGrpPricingPolicyResult pricingPolicy;

    private String paymentMethodId;

    private CusGrpPaymentMethodResult paymentMethod;

    private Instant createdAt;

    private Long countCus;

    private String description;

    private Integer discount;


}
