package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.contact.ContactSupplierService;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.customers.AddressService;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.*;
import vn.sapo.supplier.excel.ImportExcelSupplierParam;
import vn.sapo.supplier.excel.ResponseMessage;
import vn.sapo.supplier.excel.SupplierExcelService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierAPI {
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactSupplierService contactService;

    @Autowired
    private SupplierService supplierService;


//    @Autowired
//    ExcelServiceSupplier excelServiceSupplier;

    @Autowired
    SupplierExcelService supplierExcelService;

    @GetMapping
    public ResponseEntity<?> getAllSupplierPage(@RequestParam HashMap<String, String> hashMap) {
        return new ResponseEntity<>(supplierService.getAllSupplierPage(
                Integer.valueOf(hashMap.get("pageNo")),
                Integer.valueOf(hashMap.get("pageSize")),
                hashMap.get("status")
        ),
                HttpStatus.OK
        );
    }

    @GetMapping("/tags")
    public ResponseEntity<?> getAllSupplierTags() {
        List<String> listTags = supplierService.findTags();
        return new ResponseEntity<>(listTags, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        SupplierResult dto = supplierService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{supplierId}/contacts")
    public ResponseEntity<?> findContactBySupplierId(@PathVariable Integer supplierId) {
        List<ContactResult> dto = contactService.findContactsBySupplierId(supplierId);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @PostMapping("/{supplierId}/contacts")
    public ResponseEntity<?> findContactBySupplierId(@PathVariable Integer supplierId, @Valid @RequestBody CreateContactParam createParam) {
        ContactResult dto = contactService.createContactBySupplierId(supplierId, createParam);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateSupplierParam createSupplierParam) {
        SupplierResult dto = supplierService.create(createSupplierParam);
        CreateAddressParam createAddressParam = createSupplierParam.getCreateAddressParam();
        if (createAddressParam == null)
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        createAddressParam.setSupplierId(dto.getId());
        addressService.create(createAddressParam);
        dto = supplierService.findById(dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterSupplier(@RequestBody SupplierFilter supplierFilter) {
        Map<String, Object> supplierResult = supplierService.findAllByFilters(supplierFilter);
        return new ResponseEntity<>(supplierResult, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        List<ImportExcelSupplierParam> dtoList = supplierExcelService.extractExcel(file);
        for(ImportExcelSupplierParam importExcelSupplierParam: dtoList){
            if(importExcelSupplierParam.getSupGroupCode()==null || importExcelSupplierParam.getSupGroupCode().isEmpty()){
                importExcelSupplierParam.setSupGroupCode("MACDINH");
            }
        }
        supplierExcelService.fillFieldDto(dtoList);
        supplierExcelService.importSupplier(dtoList);
        String message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @PostMapping("/activeBulk")
    public ResponseEntity<?> updateStatusToAvailable(@RequestBody Integer id) {
        String supplierCode = supplierService.findSupplierCodeById(id);
        try {
            supplierService.changeStatusToAvailable(id, true);
            String finalMessage = String.format(" '%s'- Đã được cập nhật trạng thái thành công", supplierCode);
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", finalMessage);
                put("supplierCode", supplierCode);
            }}
                    , HttpStatus.OK);

        } catch (RuntimeException e) {
            e.printStackTrace();
            String finalMessage = String.format(" '%s'- Có lỗi xảy ra", supplierCode);
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", finalMessage);
                put("supplierCode", supplierCode);
            }},
                    HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/disableBulk")
    public ResponseEntity<?> updateStatusUnavailable(@RequestBody Integer id) {
        String supplierCode = supplierService.findSupplierCodeById(id);
        try {
            supplierService.changeStatusToAvailable(id, false);
            String finalMessage = String.format(" %s - Đã ngừng giao dịch thành công", supplierCode);
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", finalMessage);
                put("supplierCode", supplierCode);
            }}
                    , HttpStatus.OK);

        } catch (RuntimeException e) {
            e.printStackTrace();
            String finalMessage = String.format(" %s - Có lỗi xảy ra", supplierCode);
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", finalMessage);
                put("supplierCode", supplierCode);
            }},
                    HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/updateBulk")
    public ResponseEntity<?> updateBulkaction(@RequestBody UpdateMultiSupParam updateMultiSupParam) {
        String supplierCode = supplierService.findSupplierCodeById(updateMultiSupParam.getId());
        try {
            supplierService.changeEmpIdAndPaymentMethod(updateMultiSupParam.getId(), updateMultiSupParam.getEmployeeId(), updateMultiSupParam.getPaymentMethodId());
            String finalMessage = String.format(" '%s'- Đã được cập nhật thành công", supplierCode);
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", finalMessage);
                put("supplierCode", supplierCode);
            }}
                    , HttpStatus.OK);

        } catch (RuntimeException e) {
            e.printStackTrace();
            String finalMessage = String.format(" '%s'- Có lỗi xảy ra", supplierCode);
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", finalMessage);
                put("supplierCode", supplierCode);
            }},
                    HttpStatus.EXPECTATION_FAILED);
        }

    }

    ;

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateSupplierParam updateSupplierParam) {
        return new ResponseEntity<>(supplierService.update(updateSupplierParam), HttpStatus.OK);
    }


    @DeleteMapping("/deleteBulk")
    public ResponseEntity<?> deleteById(@RequestBody Integer id) {
        String supplierCode = supplierService.findSupplierCodeById(id);
        try {
            if (!addressService.findBySupplierId(id).isEmpty())
                addressService.deleteAllBySupplierId(id);
            supplierService.deleteById(id);
            String finalMessage = String.format(" %s - Đã được xóa thành công", supplierCode);
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", finalMessage);
                put("supplierCode", supplierCode);
            }},
                    HttpStatus.OK);

        } catch (RuntimeException e) {
            e.printStackTrace();
            String finalMessage = String.format(" %s - Có lỗi xảy ra", supplierCode);
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", finalMessage);
                put("supplierCode", supplierCode);
            }},
                    HttpStatus.EXPECTATION_FAILED);
        }

    }


//    @DeleteMapping("/suppliers/DeleteAddress")
//    public ResponseEntity<?> deleteAddressSupplier(@RequestBody List<Integer> arrayIdSupplier) {
//        addressService.deleteSoftSupplier(arrayIdSupplier);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}