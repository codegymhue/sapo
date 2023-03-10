package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.customer.CustomerExcelExporter;
import vn.sapo.customer.CustomerExcelExporterInventory;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.employee.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/customers")
public class CustomerController {
    @Autowired
    CustomerAPI customerAPI;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerGroupService customerGroupService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String showListCustomerPage() {
        return "/admin/customer/list_customer";
    }

    @GetMapping("/create")
    public String showCustomerCreatePage() {
        return "/admin/customer/create_customer";
    }

//    @GetMapping("/{id}")
//    public ModelAndView showCustomerInfoPage(@PathVariable Integer id) {
//        ModelAndView modelAndView = new ModelAndView();
//        try{
//            CustomerResult customer = customerService.findById(id);
////            customerAPI.setData(customer);
//            modelAndView.addObject("customer", customer);
//        }catch (Exception ex){
//            modelAndView.addObject("errors", ex.getMessage());
//        }
//        modelAndView.setViewName("/admin/customer/info_customer");
//        return modelAndView;
//    }

    @GetMapping("/{id}/edit")
    public ModelAndView showCustomerEditPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customer", customerService.findById(id));
        modelAndView.addObject("group", customerGroupService.findAll());
        modelAndView.addObject("employee", employeeService.findAll());
        modelAndView.setViewName("/admin/customer/edit_customer");
        return modelAndView;
    }

    //export excel file

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Customers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<CustomerResult> listCustomers = customerService.findAll();
        CustomerExcelExporter excelExporter = new CustomerExcelExporter(listCustomers);
        excelExporter.export(response);
    }

    @GetMapping("/excel")
    public void exportItemToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Customers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<CustomerResult> listCustomers = customerService.findAll();
        CustomerExcelExporterInventory excelExporter = new CustomerExcelExporterInventory(listCustomers);
        excelExporter.export(response);
    }

}