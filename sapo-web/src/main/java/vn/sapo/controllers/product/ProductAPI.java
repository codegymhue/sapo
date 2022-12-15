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


    // kien dang chinh
    @GetMapping("getAllProductPage")
    public ResponseEntity<?> getAllProductPage(@RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        //Lan sau de nghi anh khong comment code ham dang chay___ CCCCCUUUUUU
        // return new ResponseEntity<>(productService.getAllProductItemPage(pageNo, pageSize), HttpStatus.OK);
        return null;
    }


    //TODO:ANh Doi Path thanh parameter dum em
    @GetMapping("/{pageNo}/{pageSize}/{search}/{categoryId}/{brandId}/{status}")
    public ResponseEntity<?> getAllProductPageNoCategory(@PathVariable Integer pageNo,
                                                         @PathVariable Integer pageSize,
                                                         @PathVariable String search,
                                                         @PathVariable Integer categoryId,
                                                         @PathVariable Integer brandId,
                                                         @PathVariable String status
    ) {
        return new ResponseEntity<>(productService.getAllProductItemPage(pageNo, pageSize, search, categoryId, brandId, status), HttpStatus.OK);
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
        ProductResult productResult = productService.create(productWithImageParam);
        System.out.println(productResult);
        return new ResponseEntity<>(productResult, HttpStatus.CREATED);
    }

    @PostMapping("/create-short")
    public ResponseEntity<?> create(@RequestBody ProductShortParam productShortParam) {
        productService.createShortProduct(productShortParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}