package vn.sapo.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class HomeAPI {

    @RequestMapping()
    public String showDashboardPage(){
        return "/admin/home/dashboard";
    }
}
