package vn.sapo.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/customer_groups")
public class CustomerGroupController {

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
    public String showUpCusGrpPage(@PathVariable Integer id) {
        return "/admin/customer/up_cus_Group";
    }
}
