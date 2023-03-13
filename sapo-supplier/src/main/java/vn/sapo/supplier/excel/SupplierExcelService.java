package vn.sapo.supplier.excel;

import org.springframework.web.multipart.MultipartFile;
import vn.sapo.supplier.excel.ImportExcelSupplierParam;

import java.util.List;

public interface SupplierExcelService {
    List<ImportExcelSupplierParam> extractExcel(MultipartFile excelFile);

    void fillFieldDto(List<ImportExcelSupplierParam> dtoList);

    void importSupplier(List<ImportExcelSupplierParam> importExcelSupplierParams);
}
