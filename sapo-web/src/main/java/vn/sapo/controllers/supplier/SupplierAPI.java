package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.CreateAddressParam;
<<<<<<< HEAD
import vn.sapo.excel.ExcelHelperSuppliers;
import vn.sapo.excel.ExcelServiceSupplier;
import vn.sapo.excel.ResponseMessage;
=======
import vn.sapo.controllers.customer.CustomerAPI;
import vn.sapo.customer.dto.CustomerResult;
>>>>>>> supplier
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierFilter;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierAPI {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private AddressService addressService;

    @Autowired
    ExcelServiceSupplier excelServiceSupplier;

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
    public ResponseEntity<?> create(@Validated @RequestBody CreateSupplierParam createSupplierParam) {
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
    public ResponseEntity<?> testFilter(@RequestBody SupplierFilter supplierFilter) {
        Map<String, Object> supplierResult = supplierService.findAllByFilters2(supplierFilter);
        return new ResponseEntity<>(supplierResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody UpdateSupplierParam updateSupplierParam) {
        return new ResponseEntity<>(supplierService.update(updateSupplierParam), HttpStatus.OK);
    }

<<<<<<< HEAD
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelperSuppliers.hasExcelFormat(file)) {
            try {
                List<CreateSupplierParam> supplierParams = excelServiceSupplier.save(file);
                supplierParams.forEach(param -> create(param));
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


=======
<<<<<<< HEAD
=======
>>>>>>> supplier

//    public void setData(SupplierResult supplier) {
//        BigDecimal spendTotal = getSpendTotalBySupplierId(suppplier.getId());
//        BigDecimal paidTotal = getPaidTotalBySupplierId(supplier.getId());
//        supplier.setSpendTotal(spendTotal);
//        supplier.setDebtTotal(spendTotal.subtract(paidTotal));
//        supplier.setQuantityProductOrder(getQuantityProductOrderBySupplierId(supplier.getId()));
//        supplier.setQuantityItemOrder(getQuantityItemSupplierOrderById(supplier.getId()));
//        supplier.setLastDayOrder(getLastDayOrderBySupllierId(supplier.getId()));
//    }
//
//    public BigDecimal getSpendTotalBySupplierId(Integer supplierId) {
//        BigDecimal spendTotal = saleOrderService.getSpendTotalBySupplierId(supplierId);
//        if (spendTotal == null)
//            spendTotal = BigDecimal.valueOf(0);
//        return spendTotal;
//    }
//
//    public BigDecimal getPaidTotalBySupplierId(Integer supplierId) {
//        BigDecimal paidTotal = paymentSaleOrderService.getPaidTotalBySupplierId(supplierId);
//        if (paidTotal == null)
//            paidTotal = BigDecimal.valueOf(0);
//        return paidTotal;
//    }
//
//    public Integer getQuantityProductOrderBySupplierId(Integer supplierId) {
//        Integer quantityProductOrder = saleOrderService.getQuantityProductOrder(supplierId);
//        if (quantityProductOrder == null)
//            quantityProductOrder = 0;
//        return quantityProductOrder;
//    }
//
//    public Integer getQuantityItemSupplierOrderById(Integer supplierId) {
//        Integer quantityItemOrder = orderItemService.getQuantityItemSupplierOrderById(supplierId);
//        if (quantityItemOrder == null)
//            quantityItemOrder = 0;
//        return quantityItemOrder;
//    }
>>>>>>> tai_dev
}