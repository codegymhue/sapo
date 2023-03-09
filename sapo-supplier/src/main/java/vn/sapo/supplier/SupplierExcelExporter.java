//
//
//package vn.sapo.supplier;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import vn.sapo.address.dto.AddressResult;
//import vn.sapo.supplier.dto.SupGroupResult;
//import vn.sapo.supplier.dto.SupplierResult;
//import vn.sapo.supplier.excel.ExcelHeaderSupplier;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import static vn.sapo.supplier.excel.ExcelHeaderSupplier.*;
//
//
//public class SupplierExcelExporter {
//
//    private XSSFWorkbook workbook;
//
//    private XSSFSheet sheet;
//
//    private List<SupplierResult> supplierList;
//
//    public SupplierExcelExporter(List<SupplierResult> supplierList) {
//        this.supplierList = supplierList;
//        workbook = new XSSFWorkbook();
//    }
//
//
//    private void writeHeaderLine() {
//        sheet = workbook.createSheet("Suppliers");
//        Row row = sheet.createRow(0);
//
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setBold(true);
//        font.setFontHeight(14);
//        style.setFont(font);
//        createCell(row, FULL_NAME, style);
//        createCell(row, SUPPLIER_CODE , style);
//        createCell(row, SUP_GROUP_CODE, style);
//        createCell(row, EMAIL, style);
//        createCell(row, PHONE, style);
//        createCell(row, WEBSITE, style);
//        createCell(row, FAX, style);
//        createCell(row, TAX_CODE,style);
//        createCell(row, DESCRIPTION,style);
//        createCell(row, PRICE_POLICY,style);
//        createCell(row, PAYMENT_TERN,style);
//        createCell(row, PAYMENT_METHOD_TITLE,style);
//        createCell(row, CONTACT_NAME,style);
//        createCell(row, CONTACT_PHONE,style);
//        createCell(row, CONTACT_EMAIL,style);
//        createCell(row, LABEL,style);
//        createCell(row, LINE1,style);
//        createCell(row, LINE2,style);
//        createCell(row, PROVINCE_NAME,style);
//        createCell(row, DISTRICT_NAME,style);
//        createCell(row, 20,style);
//        createCell(row, 21,style);
//
//
//    }
//
//    private void createCell(Row row, ExcelHeaderSupplier, Object value, CellStyle style) {
//        byte ExcelHeaderSupplier = 0;
//        sheet.autoSizeColumn(ExcelHeaderSupplier);
//        Cell cell = row.createCell(ExcelHeaderSupplier);
//        if (value instanceof Integer) {
//            cell.setCellValue((Integer) value);
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        } else if (value instanceof Long) {
//            cell.setCellValue((Long) value);
//        } else {
//            cell.setCellValue((String) value);
//        }
//        cell.setCellStyle(style);
//    }
//
//    private void writeDataLines() {
//        int rowCount = 1;
//
//
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight(14);
//        style.setFont(font);
//
//        for (SupplierResult supplier : supplierList) {
//
//            Row row = sheet.createRow(rowCount++);
//            int columnCount = 0;
//            String fullName = supplier.getFullName();
//            createCell(row, columnCount++, FULL_NAME != null ? FULL_NAME : "", style);
//            createCell(row, columnCount++, supplier.getSupplierCode(), style);
//            SupGroupResult group = supplier.getGroup();
//            if (group != null) {
//                String title = group.getSupGroupCode();
//                createCell(row, columnCount++, title, style);
//            }
//            else {
//                createCell(row, columnCount++, null, style);
//
//            }
//            String email = supplier.getEmail();
//            createCell(row, columnCount++, email != null ? email : "", style);
//            String phone = supplier.getPhone();
//            createCell(row, columnCount++, phone != null ? phone : "", style);
//            String website = supplier.getWebsite();
//            createCell(row, columnCount++, website != null ? website : "", style);
//            createCell(row, columnCount++, supplier.getFax(), style);
//            createCell(row, columnCount++, supplier.getTaxCode(), style);
//            createCell(row, columnCount++, supplier.getDescription(), style);
//            String paymentMethodId = "";
//            if (supplier.getPaymentMethod() != null) {
//                paymentMethodId = supplier.getPaymentMethod().getTitle();
//                createCell(row, columnCount++, paymentMethodId != null ? paymentMethodId : "", style);
//            }
//            if (supplier.getAddresses().size() == 0) {
//                createCell(row, columnCount++, "", style);
//                createCell(row, columnCount++, "", style);
//                createCell(row, columnCount++, "", style);
//                createCell(row, columnCount++, "", style);
//                createCell(row, columnCount++, "", style);
//            } else {
//                for (AddressResult adr : supplier.getAddresses()) {
//                    createCell(row, columnCount++, adr.getFullName() != null ? adr.getFullName() : "", style);
//                    createCell(row, columnCount++, adr.getPhoneNumber() != null ? adr.getPhoneNumber() : "", style);
//                    createCell(row, columnCount++, adr.getEmail() != null ? adr.getEmail() : "", style);
//                    createCell(row, columnCount++, adr.getLabel() != null ? adr.getLabel() : "", style);
//                    createCell(row, LINE1, adr.getLine1() != null ? adr.getLine1() : "", style);
//
//                    if (adr.getLine2() != null)
//                        createCell(sheet.createRow(rowCount++), columnCount - 1, adr.getLine2(), style);
//                    createCell(row, columnCount++, adr.getProvinceName() != null ? adr.getProvinceName() : "", style);
//                    createCell(row, columnCount++, adr.getDistrictName() != null ? adr.getDistrictName() : "", style);
//                }
//            }
//        }
//    }
//
//    public void export(HttpServletResponse response) throws IOException {
//        writeHeaderLine();
//        writeDataLines();
//
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//
//        outputStream.close();
//    }
//}
//
