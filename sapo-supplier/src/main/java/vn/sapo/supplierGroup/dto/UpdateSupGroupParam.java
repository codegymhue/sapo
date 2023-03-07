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
public class UpdateSupGroupParam {
    @NotNull
    private Integer id;

    @NullOrNotBlank(message = "Tên nhóm nhà cung cấp không được để trống")
    private String title;

    @NullOrNotBlank(message = "Mã nhóm nhà cung cấp không được để trống")
    private String supGroupCode;

    @NullOrNotBlank
    private String description;
}
