package vn.sapo.controllers.customer;

<<<<<<< HEAD
=======

>>>>>>> main
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CustomerResult;

import java.util.Optional;

@Controller
<<<<<<< HEAD
@RequestMapping("/admin")
=======
@RequestMapping("/admin/customers")
>>>>>>> main
public class CustomerController {

    @Autowired
    CustomerService customerService;

<<<<<<< HEAD
    @GetMapping("/customers")
=======
    @GetMapping("")
>>>>>>> main
    public String showListCustomerPage() {
        return "/admin/customer/list_customer";
    }

<<<<<<< HEAD
    @GetMapping("/customer_groups")
=======
    @GetMapping("/customer_group")
>>>>>>> main
    public String showCustomerGroupPage() {
        return "/admin/customer/customer_group";
    }

<<<<<<< HEAD
    @GetMapping("customers/create")
=======
    @GetMapping("/create")
>>>>>>> main
    public String showCustomerCreatePage() {
        return "/admin/customer/create_customer";
    }



<<<<<<< HEAD
    @GetMapping("customers/history")
=======
    @GetMapping("/history")
>>>>>>> main
    public String showCustomerHistoryPage() {
        return "/admin/customer/history_customer";
    }

<<<<<<< HEAD
    @GetMapping("/customers/customerInfo/{id}")
=======
    @GetMapping("/customerInfo/{id}")
>>>>>>> main
    public ModelAndView showCustomerInfoPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerResult iCustomer = customerService.findById(id);
        modelAndView.addObject("customer", iCustomer);
        modelAndView.setViewName("/admin/customer/history_customer");
        return modelAndView;
    }

<<<<<<< HEAD
    @GetMapping("customers/edit/{id}")
=======
    @GetMapping("/edit/{id}")
>>>>>>> main
    public ModelAndView showCustomerEditPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<CustomerResult> customerOptional = Optional.ofNullable(customerService.findById(id));
        System.out.println(customerOptional.get());
        modelAndView.addObject("customer", customerOptional);
        modelAndView.setViewName("/admin/customer/edit_customer");
        return modelAndView;
    }

}