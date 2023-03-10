package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class CreateSupplierParam extends BaseSupplierParam {
    @NullOrNotBlank(message = "{supplier.validation.supplierCode.notBlank}")
    private String supplierCode;
    private BigDecimal debtTotal;
    private CreateAddressParam createAddressParam;

}