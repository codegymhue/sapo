package vn.sapo.employee.dto;

import lombok.*;

import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EmployeeResult {
    private Integer id;
    private String fullName;
    private String email;
    private String birthday;
    private String gender;
}
