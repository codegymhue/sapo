package vn.sapo.controllers.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.sapo.customerGroup.dto.DataTablesInput;
import vn.sapo.customerGroup.dto.DataTablesOutput;
import vn.sapo.customers.AddressService;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.customers.dto.DeleteAddressResult;
import vn.sapo.customers.dto.UpdateAddressParam;

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

    @PostMapping("/{id}/customer")
    private ResponseEntity<?> createAddressWithCustomerId(@RequestBody @Validated CreateAddressParam createAddressParam,
                                                          @PathVariable Integer id) {

        AddressResult addressResult = addressService.createAddressWithCustomerId(createAddressParam, id);

        return new ResponseEntity<>(addressResult, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/customer/pagination")
    private ResponseEntity<?> getContactsPagination(@PathVariable Integer id,
                                                    @RequestBody @Validated DataTablesInput input) {
        int draw = input.getDraw();
        int start = input.getStart();
        int length = input.getLength();
        int page = start / length + 1;

        Sort s = Sort.by(Sort.Direction.DESC, "id");

        Pageable pageable = PageRequest.of(page - 1, length, s);

        Page<AddressResult> dtoPage =
                addressService.findAllAddresses(id, pageable);

        DataTablesOutput<AddressResult> output = new DataTablesOutput<AddressResult>()
                .setDraw(draw)
                .setRecordsTotal(dtoPage.getTotalElements())
                .setRecordsFiltered(dtoPage.getTotalElements())
                .setData(dtoPage.getContent());

        return new ResponseEntity<>(output, HttpStatus.OK);
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

    @DeleteMapping("/{id}/customer-addresses")
    private ResponseEntity<?> deleteCustomerAddresses(@PathVariable Integer id,
                                                      @RequestBody List<Integer> listAddressId) {
        DeleteAddressResult result= addressService.deleteAddressesByListId(id, listAddressId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
