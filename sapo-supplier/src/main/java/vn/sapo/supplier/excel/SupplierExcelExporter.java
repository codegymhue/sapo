
package vn.sapo.supplier.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.supplier.dto.SupGroupResult;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.excel.ExcelHeaderSupplier;
import vn.sapo.supplier.dto.SupGroupResult;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.supplier.dto.SupplierResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static vn.sapo.supplier.excel.ExcelHeaderSupplier.*;


public class SupplierExcelExporter {

    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private List<SupplierResult> supplierList;

    public SupplierExcelExporter(List<SupplierResult> supplierList) {
        this.supplierList = supplierList;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Suppliers");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        createCell(row, 0, "Tên nhà cung cấp ", style);
        createCell(row, 1, "Mã nhà cung cấp", style);
        createCell(row, 2, "Mã nhóm nhà cung cấp", style);
        createCell(row, 3, "Email", style);
        createCell(row, 4, "Điện thoại", style);
        createCell(row, 5, "Website", style);
        createCell(row, 6, "FAX", style);
        createCell(row, 7, "Mã số thuế", style);
        createCell(row, 8, "Mô tả", style);
        createCell(row, 9, "Chính sách giá mặc định", style);
        createCell(row, 10, "Kỳ hạn thanh toán mặc định", style);
        createCell(row, 11, "Phương thức thanh toán mặc định", style);
        createCell(row, 12, "Người liên hệ ", style);
        createCell(row, 13, "SDT người liên hệ ", style);
        createCell(row, 14, "Email người liên hệ", style);
        createCell(row, 15, "Nhãn", style);
        createCell(row, 16, "Địa chỉ 1", style);
        createCell(row, 17, "Địa chỉ 2", style);
        createCell(row, 18, "Tỉnh/Thành Phố", style);
        createCell(row, 19, "Quận/Huyện", style);
        createCell(row, 20, "Nợ hiện tại", style);
        createCell(row, 21, "Tags", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;


        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (SupplierResult supplier : supplierList) {

            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            String fullName = supplier.getFullName();
            createCell(row, FULL_NAME, fullName != null ? fullName : "", style);
            createCell(row, SUPPLIER_CODE, supplier.getSupplierCode(), style);
            SupGroupResult group = supplier.getGroup();
            if (group != null)
                createCell(row, SUP_GROUP_CODE, group.getSupGroupCode(), style);
            else
                createCell(row, SUP_GROUP_CODE, null, style);

            String email = supplier.getEmail();
            createCell(row, EMAIL, email != null ? email : "", style);
            String phone = supplier.getPhone();
            createCell(row, PHONE, phone != null ? phone : "", style);
            String website = supplier.getWebsite();
            createCell(row, WEBSITE, website != null ? website : "", style);
            createCell(row, FAX, supplier.getFax(), style);
            createCell(row, TAX_CODE, supplier.getTaxCode(), style);
            createCell(row, DESCRIPTION, supplier.getDescription(), style);
            createCell(row, PRICE_POLICY, "", style);
            createCell(row, PAYMENT_TERN, "", style);
            if (supplier.getPaymentMethod() != null) {
                String paymentMethodId = supplier.getPaymentMethod().getTitle();
                createCell(row, PAYMENT_METHOD_TITLE, paymentMethodId, style);
            }
            if (supplier.getAddresses().size() == 0) {
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, "", style);

            } else {
                for (AddressResult adr : supplier.getAddresses()) {
                    String province = adr.getProvinceName();
                    String district = adr.getDistrictName();
                    createCell(row, LABEL, adr.getLabel() != null ? adr.getLabel() : "", style);
                    createCell(row, LINE1, adr.getLine1() != null ? adr.getLine1() : "", style);
                    createCell(row, LINE2, adr.getLine2() != null ? adr.getLine2() : "", style);
                    createCell(row, PROVINCE_NAME, province != null ? province : "", style);
                    createCell(row, DISTRICT_NAME, district != null ? district : "", style);
                }
                for (ContactResult contact : supplier.getContacts()) {
                    createCell(row, CONTACT_NAME, contact.getFullName() != null ? contact.getFullName() : "", style);
                    createCell(row, CONTACT_PHONE, contact.getPhoneNumber() != null ? contact.getPhoneNumber() : "", style);
                    createCell(row, CONTACT_EMAIL, contact.getEmail() != null ? contact.getEmail() : "", style);
                }
            }
            createCell(row, CURRENT_DEBT, "", style);
            List<String> tags = supplier.getTags();
            if (tags.size() > 0) {
                String tagsStr = String.join(",", tags);
                createCell(row, TAGS, tagsStr, style);
            }
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}

