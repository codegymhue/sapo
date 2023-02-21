package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.sapo.supplierGroup.SupplierGroupService;
import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.EditSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import java.util.List;

@RestController
@RequestMapping("/api/supplier_groups")
public class SupplierGroupAPI {
    @Autowired
    private SupplierGroupService supplierGroupService;
    @GetMapping
    public ResponseEntity<List<SupplierGroupResult>> findAll() {
        List<SupplierGroupResult> dto = supplierGroupService.finAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<SupplierGroupResult> create(@Validated @RequestBody CreateSupGroupParam createSupGroupParam) {
        SupplierGroupResult dto = supplierGroupService.create(createSupGroupParam);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <SupplierGroupResult> findById(@PathVariable Integer id) {
        SupplierGroupResult dto = supplierGroupService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity <SupplierGroupResult> Update(@Validated @RequestBody EditSupGroupParam editSupGroupParam) {
        SupplierGroupResult dto = supplierGroupService.update(editSupGroupParam);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteById(@PathVariable Integer id) {
        supplierGroupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
