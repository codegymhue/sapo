package vn.sapo.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.brand.BrandService;
import vn.sapo.category.CategoryService;
import vn.sapo.excel.ExcelHelper;
import vn.sapo.excel.ExcelService;
import vn.sapo.excel.ResponseMessage;
import vn.sapo.product.ProductService;
import vn.sapo.product.dto.CreateProductParam;
import vn.sapo.product.dto.ProductResult;
import vn.sapo.product.dto.ProductShortParam;
import vn.sapo.product.dto.UpdateProductParam;

import java.util.*;


@RestController
@RequestMapping("/api")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    ExcelService excelService;

    @GetMapping("/products")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/page")
    public ResponseEntity<?> getAllProductPageNoCategory(@RequestParam HashMap<String, String> hashMap) {
        return new ResponseEntity<>(productService.getAllProductItemPage(
                Integer.valueOf(hashMap.get("pageNo")),
                Integer.valueOf(hashMap.get("pageSize")),
                hashMap.get("search"),
                Integer.valueOf(hashMap.get("categoryId")),
                Integer.valueOf(hashMap.get("brandId")),
                hashMap.get("status"),
                hashMap.get("sortPage[type]"),
                hashMap.get("sortPage[value]")),
                HttpStatus.OK
        );
    }

    @GetMapping("/variants")
    public ResponseEntity<?> getAllProductVariantsPage(@RequestParam HashMap<String, String> hashMap) {
        return new ResponseEntity<>(productService.getAllProductVariantPage(
                Integer.valueOf(hashMap.get("pageNo")),
                Integer.valueOf(hashMap.get("pageSize")),
                hashMap.get("search"),
                Integer.valueOf(hashMap.get("categoryId")),
                Integer.valueOf(hashMap.get("brandId")),
                hashMap.get("status"),
                hashMap.get("sortPage[type]"),
                hashMap.get("sortPage[value]")),
                HttpStatus.OK
        );
    }

    @GetMapping("/products/categories")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/brands")
    public ResponseEntity<?> getAllBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        ProductResult productResult = productService.findById(id);
        return new ResponseEntity<>(productResult, HttpStatus.OK);
    }

    @PostMapping ("/products/checkInventory")
    public ResponseEntity<?> getAllProductCheckInventory(@RequestBody List<Integer> arrayIdProduct){
        return new ResponseEntity<>(productService.getAllCheckInventoryProduct(arrayIdProduct), HttpStatus.OK);
    }

    @PostMapping("/products/create")
    public ResponseEntity<?> create(@RequestBody CreateProductParam productWithImageParam) {
        int id = productService.create(productWithImageParam).getId();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @PutMapping("/products/update")
    public ResponseEntity<?> update(@RequestBody UpdateProductParam updateProductParam) {
        productService.update(updateProductParam);
        return new ResponseEntity<>(productService.findById(updateProductParam.getId()), HttpStatus.CREATED);
    }


    @PostMapping("/products/create-short")
    public ResponseEntity<?> create(@RequestBody ProductShortParam productShortParam) {
        productService.createShortProduct(productShortParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/products/updateStatusAvailable")
    public ResponseEntity<?> updateStatusAvailable(@RequestBody List<Integer> arrayIdProduct) {
        productService.changeStatusToAvailable(arrayIdProduct,true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/products/updateStatusUnavailable")
    public ResponseEntity<?> updateStatusUnavailable(@RequestBody List<Integer> arrayIdProduct) {
        productService.changeStatusToAvailable(arrayIdProduct,false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/products/updateApplyTax/{applyTax}")
    public ResponseEntity<?> updateChangeApplytax(@PathVariable Integer applyTax, @RequestBody List<Integer> arrayIdProduct) {
        productService.saveChangeApplyTax(applyTax, arrayIdProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/products/delete")
    public ResponseEntity<?> deleteProducts(@RequestBody List<Integer> arrayIdProduct) {
        productService.deleteSoftProduct(arrayIdProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}