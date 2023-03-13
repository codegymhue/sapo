package vn.sapo.customers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateAddressParam {
    @NotNull
    private Integer id;

    private Integer customerId;

    private Integer supplierId;
    @NotBlank(message = "Tên khách hàng không được để trống")
    private String fullName;
    @NullOrNotBlank
    private String label;

    private String phoneNumber;
    private String email;

    private String line1;

    private String line2;

    private Integer wardId;

    private String wardName;

    private Integer districtId;

    private String districtName;

    private Integer provinceId;

    private String provinceName;

    private String zipCode;
}
