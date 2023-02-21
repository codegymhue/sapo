package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.supplier.SupplierStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateSupplierParam extends BaseSupplierParam {
    @NotNull
    private Integer id;
    @NotBlank(message = "supplierCode not blank")
    private String supplierCode;
    private SupplierStatus status;

}
