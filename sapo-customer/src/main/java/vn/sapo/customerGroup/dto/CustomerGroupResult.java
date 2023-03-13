package vn.sapo.customerGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerGroupResult {

    private Integer id;

    private String title;

    private String cusGrpCode;

    private Long countCus;

    private String note;

    private Integer defaultPricingPolicyId;

    private String defaultPaymentMethodId;

    private Integer defaultDiscountRate;
    //TODO: SUA LAI UI

    private String type;

    private Instant updatedAt;

    private Instant createdAt;


}
