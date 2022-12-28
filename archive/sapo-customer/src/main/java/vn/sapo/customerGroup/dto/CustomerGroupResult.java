package vn.sapo.customerGroup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class CustomerGroupResult {
    private Integer id;

    private String title;

    private String cusGrpCode;
}
