package vn.sapo.customerGroup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CreateCusGroupParam {
    private Integer id;

    private String title;

    private String cusGrpCode;

}
