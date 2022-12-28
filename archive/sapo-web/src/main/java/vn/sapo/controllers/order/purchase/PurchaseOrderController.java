package vn.sapo.controllers.order.purchase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/purchase_orders")
public class PurchaseOrderController {

    @GetMapping("")
    public ModelAndView showListPurchase() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/purchase_orders/purchaseList");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/purchase_orders/create/purchase_orders_create");
        return modelAndView;
    }
}
