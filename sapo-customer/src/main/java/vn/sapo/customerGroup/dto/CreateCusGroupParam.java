package vn.sapo.customerGroup.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CreateCusGroupParam {

    @NotBlank(message = "{customer_group.validation.title.notBlank}")
    @Length(max = 250, message = "{customer_group.validation.title.length}")
    private String title;

    @NullOrNotBlank
    @Length(max = 50, message = "{customer_group.validation.cusGrpCode.length}")
    private String cusGrpCode;

    private Integer defaultPricingPolicyId;

    @Min(value = 0, message = "{customer_group.validation.defaultDiscountRate.min}")
    @Max(value = 100, message = "{customer_group.validation.defaultDiscountRate.max}")
    private Integer defaultDiscountRate;

    @NullOrNotBlank
    private String defaultPaymentMethodId;

    @Length(max = 255, message = "{customer_group.validation.note.max}")
    private String note;
}
