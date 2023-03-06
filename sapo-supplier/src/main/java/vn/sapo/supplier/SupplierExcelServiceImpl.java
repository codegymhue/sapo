package vn.sapo.supplier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.customers.AddressService;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.supplier.excel.ImportExcelSupplierParam;

import java.io.IOException;
import java.util.*;

import static vn.sapo.supplier.excel.ExcelHeaderSupplier.*;

@Service
public class SupplierExcelServiceImpl implements SupplierExcelService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    AddressService addressService;

    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean isExcelFile(MultipartFile file) {
        return !TYPE.equals(file.getContentType());
    }

    @Override
    public List<ImportExcelSupplierParam> extractExcel(MultipartFile excelFile) {
        if (isExcelFile(excelFile)) throw new IllegalArgumentException("file not excel");
        List<ImportExcelSupplierParam> importExcelList = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            // skip header start =1
            rows.next();
            int count = 0;
            while (count <= 5000 && rows.hasNext()) {
                Row row = rows.next();
                Cell cell = row.getCell(FULL_NAME);
                String fullName = cell == null ? null : cell.getStringCellValue();
                if (fullName != null && !fullName.isBlank()) {
                    //lưu ý iterator next supplier đã hết
                    ImportExcelSupplierParam importExcel = extractSupplier(row.iterator());
                    CreateAddressParam addressParam = extractAddress(row.iterator());
                    if (addressParam.getLine1() != null)
                        importExcel.getCreateAddressParams().add(addressParam);
                    importExcelList.add(importExcel);
                } else {
                    cell = row.getCell(LINE1);
                    String line1 = cell == null ? null : cell.getStringCellValue();
                    if (line1 != null && !line1.isBlank()) {
                        Iterator<Cell> cellsInRow = row.iterator();
                        CreateAddressParam addressParam = extractAddress(cellsInRow);
                        if (importExcelList.size() > 0) {
                            ImportExcelSupplierParam importExcel = importExcelList.get(importExcelList.size() - 1);
                            importExcel.getCreateAddressParams().add(addressParam);
                        }
                    }
                }

                count++;
            }
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        return importExcelList;
    }

    public CreateAddressParam extractAddress(Iterator<Cell> cells) {
        CreateAddressParam param = new CreateAddressParam();
        cells.forEachRemaining(cell -> {
            switch (cell.getColumnIndex()) {
                case LABEL:
                    param.setLabel(cell.getStringCellValue());
                    break;
                case LINE1:
                    param.setLine1(cell.getStringCellValue());
                    break;
                case LINE2:
                    param.setLine2(cell.getStringCellValue());
                    break;
                case PROVINCENAME:
                    param.setProvinceName(cell.getStringCellValue());
                    break;
                case DISTRICTNAME:
                    param.setDistrictName(cell.getStringCellValue());
                    break;
                default:
                    break;
            }
        });
        return param;

    }

    public ImportExcelSupplierParam extractSupplier(Iterator<Cell> cells) {
        ImportExcelSupplierParam param = new ImportExcelSupplierParam();
        cells.forEachRemaining(cell -> {
            switch (cell.getColumnIndex()) {
                case FULL_NAME:
                    param.setFullName(cell.getStringCellValue());
                    break;
                case SUPPLIERCODE:
                    param.setSupplierCode(cell.getStringCellValue());
                    break;
                case 2:
                            //  ma nhom nha cung cap
                    break;
                case EMAIL:
                    param.setEmail(cell.getStringCellValue());
                    break;
                case PHONE:
                    param.setPhone(cell.getStringCellValue());
                    break;
                case WEBSITE:
                    param.setWebsite(cell.getStringCellValue());
                    break;
                case FAX:
                    param.setFax(cell.getStringCellValue());
                    break;
                case TAXCODE:
                    param.setTaxCode(String.valueOf(cell.getNumericCellValue()));
                    break;
                case DESCIPTION:
                    param.setDescription(cell.getStringCellValue());
                    break;
                case 9:
//                          chính sách giá mặc định
                    break;
                case 10:
//                      Kỳ hạn thanh toán mặc định
                    break;
                case PAYMENTMETHODTITLE:
                    param.setPaymentMethodTitle(cell.getStringCellValue());
                    break;
                case 20:
//                            nợ hiện tại
                    break;
                case 21:
//                              tags
                    break;
                default:
                    break;
            }
        });
        return param;

    }

    @Override
    @Transactional
    public void importSupplier(List<ImportExcelSupplierParam> importExcelSupplierParams) {
        List<Supplier> suppliers = new ArrayList<>();
        importExcelSupplierParams.forEach(param -> {
            Supplier supplier = supplierMapper.toModel(param);
            supplier.setEmployeeId(1);
            suppliers.add(supplier);
        });
        supplierRepository.saveAllAndFlush(suppliers).forEach(supplier -> {
            if (supplier.getSupplierCode() == null)
                supplier.setSupplierCode(CodePrefix.SUPPLIER.generate(supplier.getId()));
            for (ImportExcelSupplierParam param : importExcelSupplierParams) {
                boolean matchFullName = param.getFullName().equals(supplier.getFullName());
                String phone = param.getPhone();
                boolean matchPhone = phone != null && phone.equals(supplier.getPhone());
                if (matchFullName && matchPhone) {
                    List<CreateAddressParam> addressParams = param.getCreateAddressParams();
                    addressParams.forEach(addressParam -> {
                        addressParam.setSupplierId(supplier.getId());
                    });
                    addressService.create(addressParams);
                }
            }
        });
    }


}
