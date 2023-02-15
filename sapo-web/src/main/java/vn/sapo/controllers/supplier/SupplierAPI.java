package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.dto.CustomerDataTable;
import vn.sapo.customer.dto.CustomerFilter;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierFilter;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierAPI {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private AddressService addressService;

    //    @GetMapping
//    public ResponseEntity<List<SupplierResult>> findAll() {
//        return new ResponseEntity<>(supplierService.findAll(), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<?> getAllSupplierPage(@RequestParam HashMap<String, String> hashMap) {
        return new ResponseEntity<>(supplierService.getAllSupplierPage(
                Integer.valueOf(hashMap.get("pageNo")),
                Integer.valueOf(hashMap.get("pageSize")),
                hashMap.get("search"),
                hashMap.get("status")
        ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResult> findById(@PathVariable Integer id) {
        SupplierResult dto = supplierService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateSupplierParam createSupplierParam) {
        //TODO: xóa dòng sout
        System.out.println(createSupplierParam);
        SupplierResult dto = supplierService.create(createSupplierParam);
        CreateAddressParam createAddressParam = createSupplierParam.getCreateAddressParam();
        if (createAddressParam == null)
            return new ResponseEntity<>(dto, HttpStatus.OK);
        createAddressParam.setSupplierId(dto.getId());
        addressService.create(createAddressParam);
        dto = supplierService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> testFilter(@RequestBody SupplierFilter supplierFilter
    ) {
        Pageable pageable;
        pageable = PageRequest.of(0,10);
//        SupplierFilter supplierFilter = new SupplierFilter();
//        supplierFilter.setFilter("loi");
        Page<SupplierResult> supplierResult = supplierService.findAllByFilters(supplierFilter, pageable);

        return new ResponseEntity<>(supplierResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateSupplierParam updateSupplierParam) {
        return new ResponseEntity<>(supplierService.update(updateSupplierParam), HttpStatus.OK);
    }


}