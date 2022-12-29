package vn.sapo.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CusEmployeeResult {
    private Integer id;
    private String fullName;
}
