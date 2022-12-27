package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.product.ProductExcelExporter;
import vn.sapo.product.dto.ProductResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping("/customers")
    public String showListCustomerPage() {
        return "/admin/customer/list_customer";
    }

    @GetMapping("/customer_groups")
    public String showCustomerGroupPage() {
        return "/admin/customer/customer_group";
    }

    @GetMapping("/customer_groups/create")
    public String showCustomerGroupCreatePage() {
        return "/admin/customer/model_create_cus_group";
    }

    @GetMapping("customers/create")
    public String showCustomerCreatePage() {
        return "/admin/customer/create_customer";
    }

    @GetMapping("/history")
    public String showCustomerHistoryPage() {
        return "/admin/customer/history_customer";
    }


    @GetMapping("/customers/customerInfo/{id}")
    public ModelAndView showCustomerInfoPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerResult iCustomer = customerService.findById(id);
        modelAndView.addObject("customer", iCustomer);
        modelAndView.setViewName("/admin/customer/history_customer");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showCustomerEditPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<CustomerResult> customerOptional = Optional.ofNullable(customerService.findById(id));
        System.out.println(customerOptional.get());
        modelAndView.addObject("customer", customerOptional);
        modelAndView.setViewName("/admin/customer/edit_customer");
        return modelAndView;
    }

//    @GetMapping("customers/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=products_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//        List<CustomerResult> listCustomers = customerService.findAll();
//        ProductExcelExporter excelExporter = new ProductExcelExporter(listCustomers);
//        excelExporter.export(response);
//    }
}