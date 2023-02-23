package vn.sapo.address.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class CreateAddressParam {
    private Integer customerId;
    private Integer supplierId;
    @NotBlank(message = "Tên khách hàng không được để trống")
    private String fullName;
    @NullOrNotBlank
    private String label;
    @NullOrNotBlank
    private String phoneNumber;
    @NullOrNotBlank
    private String email;
    @NullOrNotBlank
    private String line1;
    @NullOrNotBlank
    private String line2;

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
}
