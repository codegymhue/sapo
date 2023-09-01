package vn.sapo.customers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseAddress {
    private Integer customerId;

    private Integer supplierId;

    @NullOrNotBlank(message = "Tên khách hàng không được để trống")
    @Length(max = 150, message = "{address.validation.fullName.length}")
    private String fullName;

    @NullOrNotBlank(message = "{address.validation.label.notBlank}")
    private String label;

    @NullOrNotBlank(message = "{address.validation.phoneNumber.notBlank}")
    @Pattern(regexp = "^$|[0-9]{8,15}$", message = "{address.validation.phoneNumber.pattern}")
    private String phoneNumber;

    @NullOrNotBlank
    @Pattern(regexp = "^$|[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{address.validation.email.pattern}")
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
    @Length(max = 255, message = "{address.validation.zipCode.length}")
    private String zipCode;

    private boolean isReceiveBill;

    private boolean isShipping;
}
