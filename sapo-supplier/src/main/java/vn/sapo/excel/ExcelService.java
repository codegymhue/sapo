package vn.sapo.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.CreateSupplierParam;

import java.io.IOException;
import java.util.List;

public class ExcelService {
    @Autowired
    SupplierService supplierService;

    public List<CreateSupplierParam> save (MultipartFile file){
        try {
            List<CreateSupplierParam> suppliers = ExcelHelper.excelToSuppliers(file.getInputStream());
            return suppliers;
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
