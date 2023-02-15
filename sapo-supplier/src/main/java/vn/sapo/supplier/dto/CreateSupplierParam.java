package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

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
    private String email;
    private String phone;
    private String description;
    private String paymentMethodId;
    private String taxCode;
    private String fax;
    private String website;
    private BigDecimal debtTotal;
    private CreateAddressParam createAddressParam;
}