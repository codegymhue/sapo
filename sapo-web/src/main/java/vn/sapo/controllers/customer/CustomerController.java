package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.customer.CustomerExcelExporter;
import vn.sapo.customer.CustomerExcelExporterInventory;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CustomerParamExport;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.employee.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @GetMapping("/{id}")
    public String showCustomerInfoPage(@PathVariable Integer id) {
        return "/admin/customer/info_customer";
    }

    @GetMapping("/{id}/edit")
    public String showCustomerEditPage(@PathVariable Integer id) {
        return "/admin/customer/edit_customer";
    }

    //export excel file

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response, CustomerParamExport customerParamExport) throws IOException {
        System.out.println(customerParamExport);
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Customers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Integer> listCustomerId =  customerParamExport.getListCustomerId();
        List<CustomerResult> customerResultList = new ArrayList<>();
        if(customerParamExport.getType().equals("PRESENT")) {
            for(int i=0; i<listCustomerId.size(); i++){
                customerResultList.add(customerService.findById(listCustomerId.get(i)));
            }
        }else if(customerParamExport.getType().equals("ALL")){
            customerResultList = customerService.findAll();
        }
        CustomerExcelExporter excelExporter = new CustomerExcelExporter(customerParamExport, customerResultList);
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