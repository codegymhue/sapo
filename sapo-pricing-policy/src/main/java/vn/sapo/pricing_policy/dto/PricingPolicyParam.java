package vn.sapo.pricing_policy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class PricingPolicyParam {

    @NotBlank(message = "Tên chính sách giá không được để trống")
    @Length(max = 255, message = "Tên chính sách giá không được vượt quá 255 ký tự")
    private String title;

    @NotBlank(message = "Mã chính sách giá không được để trống")
    @Length(max = 255, message = "Mã chính sách giá không được vượt quá 255 ký tự")
    private String pricingPolicyCode;

    @NotBlank(message = "Loại chính sách giá không được để trống")
    private String pricingPolicyType;

}
