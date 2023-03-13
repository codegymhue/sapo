package vn.sapo.customerGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    private String cusGrpType;

    private Integer discount;

    private Date createAtDate;

    private Integer countCus;

    private String note;

    private Integer defaultPricingPolicyId;

    private String defaultPaymentMethodId;

    private Integer defaultDiscountRate;
    //TODO: SUA LAI UI

    private String type;

    private Instant updatedAt;

    private Instant createdAt;

    public CustomerGroupResult(Integer id, String title, String cusGrpCode, String cusGrpType, Integer countCus, String note, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.cusGrpCode = cusGrpCode;
        this.cusGrpType = cusGrpType;
        this.countCus = countCus;
        this.note = note;
        this.createdAt = createdAt;
    }


//    public CustomerGroupResult(Integer id, String title, String cusGrpCode, Integer pricingPolicyId, String paymentMethodId, Date createAtDate, Integer countCus, String description, Integer discount) {
//        this.id = id;
//        this.title = title;
//        this.cusGrpCode = cusGrpCode;
//        this.pricingPolicyId = pricingPolicyId;
//        this.paymentMethodId = paymentMethodId;
//        this.createAtDate = createAtDate;
//        this.countCus = countCus;
//        this.description = description;
//        this.discount = discount;
//    }
//
//    public CustomerGroupResult(Integer id, String title, String cusGrpCode, Integer pricingPolicyId, String paymentMethodId, Instant createdAt, Integer countCus, String description, Integer discount) {
//        this.id = id;
//        this.title = title;
//        this.cusGrpCode = cusGrpCode;
//        this.pricingPolicyId = pricingPolicyId;
//        this.paymentMethodId = paymentMethodId;
//        this.createdAt = createdAt;
//        this.countCus = countCus;
//        this.description = description;
//        this.discount = discount;
//    }
}
