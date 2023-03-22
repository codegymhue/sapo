package vn.sapo.supplier_group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class SupplierGroupResult {
    private Integer id;

    private String title;

    private String supGroupCode;

    private String description;
    private String createdAt;


}
