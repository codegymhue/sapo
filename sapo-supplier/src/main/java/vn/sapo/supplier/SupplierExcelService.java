package vn.sapo.supplier;

import org.springframework.web.multipart.MultipartFile;
import vn.sapo.supplier.excel.ImportExcelSupplierParam;

import java.util.List;

public interface SupplierExcelService {
    List<ImportExcelSupplierParam> extractExcel(MultipartFile excelFile);

    void importSupplier(List<ImportExcelSupplierParam> importExcelSupplierParams);
}
