package vn.sapo.employee;

import vn.sapo.employee.dto.EmployeeResult;

import java.util.List;



public interface EmployeeService {

    List<EmployeeResult> findAll();

    EmployeeResult findById(Integer id);

}