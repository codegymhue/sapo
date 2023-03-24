package vn.sapo.customers.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CreateAddressParam {
    private Integer customerId;

    private Integer supplierId;

    @NotBlank(message = "Tên khách hàng không được để trống")
    private String fullName;

    @NullOrNotBlank(message = "Nhãn không được để trống")
    private String label;

    @NullOrNotBlank(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @NullOrNotBlank
    private String email;

    @NotBlank(message = "{address.validation.line1.notBlank}")
    @Length(max = 255, message = "{address.validation.line1.length}")
    private String line1;

    @NullOrNotBlank
    private String line2;

    private String description;

    private Integer wardId;

    @NullOrNotBlank
    private String wardName;

    private Integer districtId;

    @NullOrNotBlank
    private String districtName;

    private Integer provinceId;

    @NullOrNotBlank
    private String provinceName;

    @NullOrNotBlank
    private String zipCode;

    private boolean isReceiveBill;

    private boolean isShipping;

    private List<String> tags;


}
