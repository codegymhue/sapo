package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateMultiSupParam {
    @NotNull
    private Integer id;
    @NullOrNotBlank
    private Integer employeeId;
    @NullOrNotBlank
    private String paymentMethodId;
}
