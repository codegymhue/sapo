package vn.sapo.customerGroup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerGroupResult {
    private Integer id;
    private String title;
    private String cusGrpCode;
}
