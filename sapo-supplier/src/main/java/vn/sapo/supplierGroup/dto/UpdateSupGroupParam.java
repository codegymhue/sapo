package vn.sapo.supplierGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UpdateSupGroupParam extends BaseSupplierGroup {
    @NotNull
    private Integer id;
}
