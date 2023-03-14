package vn.sapo.supplierGroup.dto;

import lombok.Getter;
import lombok.Setter;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BaseSupplierGroup {
    @NotBlank(message = "{supplier_group.validation.title.notBlank}")
    protected String title;

    @NullOrNotBlank(message = "{supplier_group.validation.supGroupCode.notBlank}")
    protected String supGroupCode;

    @NullOrNotBlank(message = "{supplier_group.validation.note.notBlank}")
    protected String note;
}
