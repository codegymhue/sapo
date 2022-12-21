package vn.sapo.employee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CreateEmployeeParam {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String birthday;
    private String gender;
}