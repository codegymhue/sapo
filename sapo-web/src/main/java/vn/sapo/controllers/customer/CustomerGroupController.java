package vn.sapo.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.customerGroup.dto.CustomerGroupResult;

import java.util.Optional;

@Controller
@RequestMapping("/admin/customer_groups")
public class CustomerGroupController {
    @Autowired
    CustomerGroupService customerGroupService;

    @GetMapping
    public String showCustomerGroupPage() {
        return "/admin/customer/customer_group";
    }

    @GetMapping("/create")
    public String showCustomerGroupCreatePage() {
        return "/admin/customer/create_cus_group";
    }

    @GetMapping("/update")
    public String showCustomerGroupUpdatePage() {
        return "/admin/customer/up_cus_Group";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpCusGrpPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<CustomerGroupResult> cusGrpOptional = Optional.ofNullable(customerGroupService.findById(id));
        System.out.println(cusGrpOptional.get());
        modelAndView.addObject("customerGroup", cusGrpOptional.get());
        modelAndView.setViewName("/admin/customer/up_cus_Group");
        return modelAndView;
    }
}
