package vn.sapo.supplierGroup.dto;


import lombok.Getter;
import lombok.Setter;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

@Getter
@Setter
public class BaseSupplierGroup {
    @NullOrNotBlank(message = "{supplier_group.validation.title.notBlank}")
    protected String title;

    @NullOrNotBlank
    protected String supGroupCode;

    @NullOrNotBlank
    protected String description;
}
