package vn.sapo.customerGroup.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateCusGroupParam {
    private Integer id;

    private String title;

    private String cusGrpCode;
}
