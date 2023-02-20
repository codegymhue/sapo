package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class CreateSupplierParam {
    private Integer id;
    @NullOrNotBlank(message = "supplierCode not blank")
    private String supplierCode;
    @NotBlank(message = "fullName not blank")
    private String fullName;
    @NullOrNotBlank
    private String email;
    @NullOrNotBlank
    private String phone;
    private Integer groupId;
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
    private BigDecimal debtTotal;
    private CreateAddressParam createAddressParam;

}