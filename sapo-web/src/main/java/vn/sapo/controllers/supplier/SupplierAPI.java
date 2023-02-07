package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/admin/suppliers")
public class SupplierAPI {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierResult>> findAll() {
        return new ResponseEntity<>(supplierService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/histories")
    public ResponseEntity<SupplierResult> findById(@PathVariable Integer id) {
        SupplierResult dto = supplierService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SupplierResult> save(@RequestBody CreateSupplierParam supplierCreate) {
        return new ResponseEntity<>(supplierService.create(supplierCreate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/page")
    public ResponseEntity<?> getAllProductPageNoCategory(@RequestParam HashMap<String, String> hashMap) {
        Integer.valueOf(hashMap.get("pageNo"));
                Integer.valueOf(hashMap.get("pageSize"));
                hashMap.get("search");
                hashMap.get("status");
        System.out.println(hashMap);
        return new ResponseEntity<>(supplierService.getAllProductItemPage(
                Integer.valueOf(hashMap.get("pageNo")),
                Integer.valueOf(hashMap.get("pageSize")),
                hashMap.get("search"),
                hashMap.get("status")
                ),
                HttpStatus.OK
        );
    }

}