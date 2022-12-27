package vn.sapo.employee.dto;

import org.springframework.stereotype.Component;
import vn.sapo.entities.Employee;

@Component
public class EmployeeMapper {
    public EmployeeResult toDTO(Employee employee) {
        return null;
    }

    public Employee toModel(CreateEmployeeParam employeeParam) {
        return null;
    }

}