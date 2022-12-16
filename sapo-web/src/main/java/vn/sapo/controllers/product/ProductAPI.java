package vn.sapo.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.brand.BrandService;
import vn.sapo.category.CategoryService;
import vn.sapo.product.ProductService;
import vn.sapo.product.dto.CreateProductParam;
import vn.sapo.product.dto.ProductResult;
import vn.sapo.product.dto.ProductShortParam;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAllProductPageNoCategory(@RequestParam HashMap<String, String> hashMap)
    {
        return new ResponseEntity<>(productService.getAllProductItemPage(
                Integer.valueOf(hashMap.get("pageNo")),
                Integer.valueOf(hashMap.get("pageSize")),
                hashMap.get("search"),
                Integer.valueOf(hashMap.get("categoryId")),
                Integer.valueOf(hashMap.get("brandId")),
                hashMap.get("status")),
                HttpStatus.OK
        );
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<?> getAllBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        ProductResult productResult = productService.findById(id);
        return new ResponseEntity<>(productResult, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateProductParam productWithImageParam) {
        System.out.println(productWithImageParam);
        ProductResult p = productService.create(productWithImageParam);
        return new ResponseEntity<>(productService.findById(p.getId()), HttpStatus.CREATED);
    }

    @PostMapping("/create-short")
    public ResponseEntity<?> create(@RequestBody ProductShortParam productShortParam) {
        productService.createShortProduct(productShortParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateStatusAvailable")
    public ResponseEntity<?> updateStatusAvailable(@RequestBody List<String> arrayIdProduct) {
        productService.saveChangeStatusToAvailable(arrayIdProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateStatusUnavailable")
    public ResponseEntity<?> updateStatusUnavailable(@RequestBody List<String> arrayIdProduct) {
        productService.saveChangeStatusToUnavailable(arrayIdProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody List<String> arrayIdProduct){
        productService.deleteSoftProduct(arrayIdProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}