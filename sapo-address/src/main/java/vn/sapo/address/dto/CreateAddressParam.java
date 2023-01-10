package vn.sapo.address.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class CreateAddressParam {
    @NotBlank(message = "Trường customer_ID bị null")
    private Integer customerId;
    private Integer supplierId;
    @NotBlank(message = "Tên khách hàng không được để trống")
    private String fullName;
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

    private boolean isReceiveBill;
    private boolean isShipping;
}
