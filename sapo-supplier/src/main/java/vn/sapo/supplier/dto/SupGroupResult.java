package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SupGroupResult {
    private Integer id;
    private String title;
    private String supGroupCode;
}
