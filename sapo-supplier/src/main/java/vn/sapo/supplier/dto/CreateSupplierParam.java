package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class CreateSupplierParam extends BaseSupplierParam {
    @NullOrNotBlank(message = "{supplier.validation.supplierCode.notBlank}")
    private String supplierCode;
    private BigDecimal debtTotal;
    private CreateAddressParam createAddressParam;

    @NullOrNotBlank(message = "{supplier.validation.line1.notBlank}")
    private String line1;

    @Pattern(message = "{supplier.validation.email.pattern}", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

//    @Pattern(message = "{supplier.validation.fax.pattern}", regexp = "/^\\d+$/")
    private String fax;

//    @Pattern(message = "{supplier.validation.phone.pattern}", regexp = "/^\\d+$/")
    private String phone;
}