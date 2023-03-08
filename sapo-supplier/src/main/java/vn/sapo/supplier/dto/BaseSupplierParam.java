package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseSupplierParam {

    @NotBlank(message = "Tên nhà cung cấp không được để trống")
    private String fullName;
    @NullOrNotBlank
    private String email;
    @NullOrNotBlank
    private String phone;
    private Integer groupId;
    @NullOrNotBlank
    private String description;
    private Integer employeeId;
    @NullOrNotBlank
    private String paymentMethodId;
    @NullOrNotBlank
    private String taxCode;
    @NullOrNotBlank
    private String fax;
    @NullOrNotBlank
    private String website;
}
