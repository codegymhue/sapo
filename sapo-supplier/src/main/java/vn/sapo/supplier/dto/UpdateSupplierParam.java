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
public class UpdateSupplierParam extends AbstractSupplierParam {
    @NotNull
    private Integer id;
    @NotBlank(message = "supplierCode not blank")
    private String supplierCode;
    private SupplierStatus status;

}
