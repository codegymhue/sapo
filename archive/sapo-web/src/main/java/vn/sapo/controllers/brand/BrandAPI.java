package vn.sapo.controllers.brand;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.brand.BrandService;
import vn.sapo.brand.dto.CreateBrandParam;
import vn.sapo.brand.dto.BrandResult;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandAPI {

    @Autowired
    BrandService brandService;

    @GetMapping("")
    public ResponseEntity<?> showAllBrand(){
        List<BrandResult> brands =  brandService.findAll();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createBrand(@RequestBody CreateBrandParam brandParam){
        BrandResult brand = brandService.create(brandParam);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }
}
