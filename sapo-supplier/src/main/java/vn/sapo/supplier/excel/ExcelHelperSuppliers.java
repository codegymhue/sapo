package vn.sapo.supplier.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.supplier.dto.CreateSupplierParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelperSuppliers {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADER = {"Tên nhà cung cấp", "Mã nhà cung cấp", "Mã nhóm nhà cung cấp", "Email", "Điện thoại", "Website", "FAX", "Mã số thuế", "Mô tả", "Chính sách giá mặc định", "Kỳ hạn thanh toán mặc định", "Phương thức thanh toán mặc định", "Người liên hệ", "SDT người liên hệ", "Email người liên hệ", "Nhãn", "Địa chỉ 1", "Địa chỉ 2", "Tỉnh/Thành Phố", "Quận/Huyện", "Nợ hiện tại", "Tags"};

    static String SHEET = "DuLieuNhap";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<CreateSupplierParam> excelToSuppliers(InputStream is) {
        List<CreateSupplierParam> suppliers = new ArrayList<CreateSupplierParam>();
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            // skip header start =1
            rows.next();
            int count = 0;
            while (count <= 5000 && rows.hasNext()) {
                CreateSupplierParam supplier = new CreateSupplierParam();
                CreateAddressParam address = new CreateAddressParam();
                address.setProvinceId(-1);
                address.setDistrictId(-1);
//                address.setWardId(-1);
                Row currentRow = rows.next();
                Iterator<Cell> cellsInRow = currentRow.iterator();
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (currentCell.getColumnIndex()) {
                        case 0:
                            supplier.setFullName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            supplier.setSupplierCode(currentCell.getStringCellValue());
                            break;
                        case 2:
//  ma nhom nha cung cap
                            break;
                        case 3:
                            supplier.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            supplier.setPhone(currentCell.getStringCellValue());
                            break;
                        case 5:
                            supplier.setWebsite(currentCell.getStringCellValue());
                            break;
                        case 6:
                            supplier.setFax(currentCell.getStringCellValue());
                            break;
                        case 7:
                            supplier.setTaxCode(String.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 8:
                            supplier.setDescription(currentCell.getStringCellValue());
                            break;
                        case 9:
//                     chính sách giá mặc định
                            break;
                        case 10:
//                      Kỳ hạn thanh toán mặc định
                            break;
                        case 11:
                            String paymentMethod = currentCell.getStringCellValue();
                            if (paymentMethod != null && !paymentMethod.isBlank())
                                supplier.setPaymentMethodId(paymentMethod);

                            break;

                        case 12:
                            address.setFullName(currentCell.getStringCellValue());
                            break;
                        case 13:
                            address.setPhoneNumber(currentCell.getStringCellValue());
                            break;
                        case 14:
                            address.setEmail(currentCell.getStringCellValue());
                            break;
                        case 15:
//                            nhãn
                            break;
                        case 16:
                            address.setLine1(currentCell.getStringCellValue());
                            break;
                        case 17:
                            address.setLine2(currentCell.getStringCellValue());
                            break;
                        case 18:
                            address.setProvinceName(currentCell.getStringCellValue());
                            break;
                        case 19:
                            address.setDistrictName(currentCell.getStringCellValue());
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

                }
                if (supplier.getFullName() != null) {
                    supplier.setCreateAddressParam(address);
                    suppliers.add(supplier);
                }
                count++;
            }
            workbook.close();
            return suppliers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}