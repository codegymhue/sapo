package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.supplier_group.SupplierGroupService;
import vn.sapo.supplier_group.dto.CreateSupGroupParam;
import vn.sapo.supplier_group.dto.UpdateSupGroupParam;
import vn.sapo.supplier_group.dto.SupplierGroupResult;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public ResponseEntity<?> create(@Valid @RequestBody CreateSupGroupParam createSupGroupParam) {
        SupplierGroupResult dto = supplierGroupService.create(createSupGroupParam);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> findById(@PathVariable Integer id) {
        SupplierGroupResult dto = supplierGroupService.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity <?> update(@Valid @RequestBody UpdateSupGroupParam updateSupGroupParam, BindingResult bindingResult) {
        SupplierGroupResult dto = supplierGroupService.update(updateSupGroupParam);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteById(@PathVariable Integer id) {
        supplierGroupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
