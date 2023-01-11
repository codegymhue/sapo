package vn.sapo.address.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateAddressParam {
    @NotNull
    private Integer id;

    private Integer customerId;

    private Integer supplierId;

    private String line1;

    private String line2;

    private Integer wardId;

    private String wardName;

    private Integer districtId;

    private String districtName;

    private Integer provinceId;

    private String provinceName;

    private String phoneNumber;

    private String email;

    private String zipCode;
}
