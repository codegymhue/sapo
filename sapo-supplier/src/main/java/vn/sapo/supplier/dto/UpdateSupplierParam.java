package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateSupplierParam {
    @NotNull
    private Integer id;

    @NullOrNotBlank(message = "supplierCode not blank")
    private String supplierCode;

    @NotBlank(message = "fullName not blank")
    private String fullName;
    @NullOrNotBlank
    private String email;

    @NullOrNotBlank
    private String phone;

    @NullOrNotBlank
    private String description;

    @NullOrNotBlank
    private String paymentMethodId;

    private Integer groupId;
    private Integer employeeId;
    private SupplierStatus status;

    @NullOrNotBlank
    private String taxCode;
    @NullOrNotBlank
    private String fax;
    @NullOrNotBlank
    private String website;

}
