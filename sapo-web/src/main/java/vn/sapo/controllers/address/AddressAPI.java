package vn.sapo.controllers.address;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vn.sapo.address.AddressRepository;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressAPI {
    @Autowired
    AddressService addressService;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/findByCustomerId/{id}")
    public ResponseEntity<?> findByCustomerId(@PathVariable Integer id) {
        List<AddressResult> address = addressService.findByCustomerId(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
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
}
