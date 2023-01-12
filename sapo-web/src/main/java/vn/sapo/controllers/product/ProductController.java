package vn.sapo.controllers.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.media.MediaService;
import vn.sapo.pricing_policy.PricingPolicyService;
import vn.sapo.product.ProductExcelExporter;
import vn.sapo.product.ProductExcelExporterInventory;
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

    @Autowired
    MediaService mediaService;

    @Autowired
    PricingPolicyService pricingPolicyService;

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
        ModelAndView modelAndView = new ModelAndView();
        String productPriceSaleList;
        String productPricePurchaseList;
        ObjectWriter ow1 = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ObjectWriter ow2 = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            productPriceSaleList = ow1.writeValueAsString(pricingPolicyService.findAllSale());
            productPricePurchaseList = ow2.writeValueAsString(pricingPolicyService.findAllPurchase());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        modelAndView.addObject("productPriceSaleList", productPriceSaleList);
        modelAndView.addObject("productPricePurchaseList", productPricePurchaseList);
        modelAndView.setViewName("/admin/product/product_create");
        return modelAndView;
    }

    @GetMapping("/product/edit/{id}")
    public ModelAndView showProductEditPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        ProductResult product = productService.findById(id);
        if (product == null) {
            modelAndView.addObject("errors", "errors");
        } else {
            String medias;
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            try {
                medias = ow.writeValueAsString(mediaService.findAllById(product.getId()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println(medias);
            modelAndView.addObject("medias", medias);
            modelAndView.addObject("product", productService.findById(product.getId()));
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


    @GetMapping("/price_adjustments")
    public ModelAndView showPriceAdjustmentsPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/product/price_adjustments");
        return modelAndView;
    }

    @GetMapping("/stock_transfers")
    public ModelAndView showStockTransferPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/product/stock_transfers");
        return modelAndView;
    }


    @GetMapping("/inventory_management")
    public ModelAndView showInventoryManagementPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/product/inventory_management");
        return modelAndView;
    }

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
    @GetMapping("/products/excel")
    public void exportItemToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<ProductResult> listProducts = productService.findAll();
        ProductExcelExporterInventory excelExporter = new ProductExcelExporterInventory(listProducts);
        excelExporter.export(response);
    }
}

