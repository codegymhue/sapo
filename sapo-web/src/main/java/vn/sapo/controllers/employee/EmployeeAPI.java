//package vn.sapo.controllers.employee;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/api/employees")
//public class EmployeeAPI {
//
//    @Autowired
//    EmployeeService employeeService;
//
//    @GetMapping()
//    @Transactional(readOnly = true)
//    public ResponseEntity<?> showListCustomerAll() {
//        List<EmployeeResult> employees = employeeService.findAll();
//        return new ResponseEntity<>(employees, HttpStatus.OK);
//    }
//
//
//}
