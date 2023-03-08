package vn.sapo.pricing_policy.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class PricingPolicyParam {

//    @NotBlank(message = "Tên chính sách giá không được để trống")
    private String title;

//    @NotBlank(message = "Mã chính sách giá không được để trống")
    private String pricingPolicyCode;

//    @NotBlank(message = "Loại chính sách giá không được để trống")
    private String pricingPolicyType;
}
