package vn.sapo.controllers.supplier;

import net.bytebuddy.asm.Advice;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/admin/suppliers")
public class SupplierAPI {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<SupplierResult>> findAll() {
        return new ResponseEntity<>(supplierService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}/histories")
    public ResponseEntity<SupplierResult> findById(@PathVariable Integer id) {
        SupplierResult dto = supplierService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

//    @PostMapping("/create")
//    public ResponseEntity<SupplierResult> save(@RequestBody CreateSupplierParam supplierCreate) {
//        return new ResponseEntity<>(supplierService.create(supplierCreate), HttpStatus.OK);
//
//    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateSupplierParam createSupplierParam) {
        System.out.println(createSupplierParam );

        SupplierResult dto = supplierService.create(createSupplierParam);
        CreateAddressParam createAddressParam = createSupplierParam.getCreateAddressParam();


        if (createAddressParam == null)
            return new ResponseEntity<>(dto, HttpStatus.OK);
        createAddressParam.setCustomerId(dto.getId());
        addressService.create(createAddressParam);
        dto = supplierService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/edit")
    public ResponseEntity<SupplierResult> findByIdEdit(@PathVariable Integer id) {
        SupplierResult dto = supplierService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<?> update(@RequestBody UpdateSupplierParam updateSupplierParam) {
    return new ResponseEntity<>(supplierService.update(updateSupplierParam), HttpStatus.OK);
    }
//
//    @GetMapping("/{id}/histories")
//    public ResponseEntity<SupplierResult> findById(@PathVariable Integer id) {
//        return new ResponseEntity<>(supplierService.findById(id), HttpStatus.OK);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<SupplierResult> save(@RequestBody CreateSupplierParam supplierCreate) {
//        return new ResponseEntity<>(supplierService.create(supplierCreate), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}/delete")
//    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
//        supplierService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @GetMapping("/products/page")
    public ResponseEntity<?> getAllProductPageNoCategory(@RequestParam HashMap<String, String> hashMap) {
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