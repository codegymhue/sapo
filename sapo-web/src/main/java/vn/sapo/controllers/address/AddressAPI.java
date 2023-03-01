package vn.sapo.controllers.address;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.address.AddressRepository;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;

import javax.persistence.Index;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressAPI {
    @Autowired
    AddressService addressService;

    @GetMapping("/findByCustomerId/{customerId}")
    public ResponseEntity<?> findByCustomerId(@PathVariable Integer customerId) {
        List<AddressResult> addresses = addressService.findByCustomerId(customerId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/findBySupplierId/{supplierId}")
    public ResponseEntity<?> findBySupplierId(@PathVariable Integer supplierId) {
        List<AddressResult> addresses = addressService.findBySupplierId(supplierId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/findBySupplierId")
    public ResponseEntity<?> findBySupplierIdForPage(@RequestParam HashMap<String, String> hashMap) {
        return new ResponseEntity<>(addressService.getAllAddressSupplierPage(
                Integer.valueOf(hashMap.get("pageNo")),
                Integer.valueOf(hashMap.get("pageSize")),
                Integer.valueOf(hashMap.get("supplierId"))
        ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(addressService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AddressResult> address = addressService.findAll();
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateAddressParam createAddressParam) {
        AddressResult address = addressService.create(createAddressParam);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateAddressParam updateAddressParam) {
        AddressResult address = addressService.update(updateAddressParam);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByAddressSupplierId(@PathVariable Integer id) {
         addressService.deleteByAddressSupplierId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<?> deleteAddressSupplier(@RequestBody List<Integer> arrayIdSupplier) {
        addressService.deleteSoftSupplier(arrayIdSupplier);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
