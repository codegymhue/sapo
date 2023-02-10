package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.supplierGroup.SupplierGroupService;
import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import java.util.List;

@RestController
@RequestMapping("/api/admin/supplier_groups")
public class SupplierGroupAPI {
    @Autowired
    private SupplierGroupService supplierGroupService;
    @GetMapping
    public ResponseEntity<List<SupplierGroupResult>> findAll() {
        List<SupplierGroupResult> dto = supplierGroupService.finAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<SupplierGroupResult> create(@RequestBody CreateSupGroupParam createSupGroupParam) {
        SupplierGroupResult dto = supplierGroupService.create(createSupGroupParam);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
