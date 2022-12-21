package vn.sapo.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.product.ProductExcelExporter;
import vn.sapo.product.ProductService;
import vn.sapo.product.dto.ProductResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping("/products")
    public ModelAndView showProductListPage() {
        return new ModelAndView("/admin/product/product_list");
    }

    @GetMapping("/variants")
    public ModelAndView showVariantsListPage() {
        return new ModelAndView("/admin/variants/variants");
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
        try {
            ProductResult product = productService.findById(id);
            modelAndView.addObject("product", product);
        } catch (Exception ex) {
            modelAndView.addObject("errors", ex.getMessage());
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

    //export excel file
    @GetMapping("/products/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<ProductResult> listProducts = productService.findAll();
        ProductExcelExporter excelExporter = new ProductExcelExporter(listProducts);
        excelExporter.export(response);
    }


}

