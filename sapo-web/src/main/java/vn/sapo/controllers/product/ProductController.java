package vn.sapo.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.product.ProductService;
import vn.sapo.product.dto.ProductDetailResult;
import vn.sapo.product.dto.ProductResult;

@RestController
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping("/products")
    public ModelAndView showProductListPage() {
        return new ModelAndView("/admin/product/product_list");
    }

    @GetMapping("/products/create")
    public ModelAndView showProductCreatePage() {
        return new ModelAndView("/admin/product/product_create");
    }
    
    @GetMapping("/product/edit/{id}")
    public ModelAndView showProductEditPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        ProductResult product = productService.findById(id);
        if (product == null) {
            modelAndView.addObject("errors", "errors");
        } else {
            modelAndView.addObject("product", productService.findDetailById(product.getId()));
        }
        modelAndView.setViewName("/admin/product/product_edit");
        return modelAndView;
    }

    @GetMapping("/product/{id}")
    public ModelAndView showDetailProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        ProductResult product = productService.findById(id);
        if (product == null) {
            modelAndView.addObject("errors", "errors");
        } else {
            ProductDetailResult productResult =productService.findDetailById(product.getId());
            modelAndView.addObject("product", productResult);
        }
        modelAndView.setViewName("/admin/product/product_detail");
        return modelAndView;
    }


    //    Điều chỉnh giá vốn
    @GetMapping("/price_adjustments")
    public ModelAndView showPriceAdjustmentsPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/product/price_adjustments");
        return modelAndView;
    }

    // Chuyển hàng
    @GetMapping("/stock_transfers")
    public ModelAndView showStockTransferPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/product/stock_transfers");
        return modelAndView;
    }


    // Quản lý kho
    @GetMapping("/inventory_management")
    public ModelAndView showInventoryManagementPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/product/inventory_management");
        return modelAndView;
    }

}

