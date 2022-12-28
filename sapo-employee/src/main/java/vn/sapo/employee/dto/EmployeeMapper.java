package vn.sapo.employee.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.Employee;

@Component
public class EmployeeMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeResult toDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeResult.class)
                .setFullName(employee.getFullName());
    }

    public Employee toModel(CreateEmployeeParam employeeParam) {
        return null;
    }

}