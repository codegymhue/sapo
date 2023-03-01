package vn.sapo.customerGroup.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
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

    private Integer pricingPolicyId;

    private String paymentMethodId;

    private Instant createdAt;
    private Date createAtDate;

    private Long countCus;

    private String description;

    private Integer discount;

    public CustomerGroupResult(Integer id, String title, String cusGrpCode, Integer pricingPolicyId, String paymentMethodId, Date createAtDate, Long countCus, String description, Integer discount) {
        this.id = id;
        this.title = title;
        this.cusGrpCode = cusGrpCode;
        this.pricingPolicyId = pricingPolicyId;
        this.paymentMethodId = paymentMethodId;
        this.createAtDate = createAtDate;
        this.countCus = countCus;
        this.description = description;
        this.discount = discount;
    }

    public CustomerGroupResult(Integer id, String title, String cusGrpCode, Integer pricingPolicyId, String paymentMethodId, Instant createdAt, Long countCus, String description, Integer discount) {
        this.id = id;
        this.title = title;
        this.cusGrpCode = cusGrpCode;
        this.pricingPolicyId = pricingPolicyId;
        this.paymentMethodId = paymentMethodId;
        this.createdAt = createdAt;
        this.countCus = countCus;
        this.description = description;
        this.discount = discount;
    }
}
