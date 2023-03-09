

package vn.sapo.supplier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vn.sapo.address.dto.AddressResult;
import vn.sapo.supplier.dto.SupGroupResult;
import vn.sapo.supplier.dto.SupplierResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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
        createCell(row, 9, "Phương thức thanh toán mặc định", style);
        createCell(row, 10, "Người liên hệ ", style);
        createCell(row, 11, "SDT người liên hệ ", style);
        createCell(row, 12, "Email người liên hệ", style);
        createCell(row, 13, "Nhãn", style);
        createCell(row, 14, "Địa chỉ 1", style);
        createCell(row, 15, "Địa chỉ 2", style);
        createCell(row, 16, "Tỉnh/Thành Phố", style);
        createCell(row, 17, "Quận/Huyện", style);
        createCell(row, 18, "Nợ hiện tại", style);
        createCell(row, 19, "Tags", style);
        createCell(row, 20, "Chính sách giá mặc định", style);
        createCell(row, 21, "Kỳ hạn thanh toán mặc định", style);

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
            createCell(row, columnCount++, fullName != null ? fullName : "", style);
            createCell(row, columnCount++, supplier.getSupplierCode(), style);
            SupGroupResult group = supplier.getGroup();
            if (group != null) {
                String title = group.getSupGroupCode();
                createCell(row, columnCount++, null, style);
            }
            if (group == null) {
                createCell(row, columnCount++, "", style);

            }

            String email = supplier.getEmail();
            createCell(row, columnCount++, email != null ? email : "", style);
            String phone = supplier.getPhone();
            createCell(row, columnCount++, phone != null ? phone : "", style);
            String website = supplier.getWebsite();
            createCell(row, columnCount++, website != null ? website : "", style);
            createCell(row, columnCount++, supplier.getFax(), style);
            createCell(row, columnCount++, supplier.getTaxCode(), style);
            createCell(row, columnCount++, supplier.getDescription(), style);
            String paymentMethodId = "";
            if (supplier.getPaymentMethod() != null) {
                paymentMethodId = supplier.getPaymentMethod().getTitle();
            }
            createCell(row, columnCount++, paymentMethodId != null ? paymentMethodId : "", style);

            if (supplier.getAddresses().size() == 0) {
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, "", style);
            } else {
                for (AddressResult adr : supplier.getAddresses()) {
                    createCell(row, columnCount++, adr.getFullName() != null ? adr.getFullName() : "", style);
                    createCell(row, columnCount++, adr.getPhoneNumber() != null ? adr.getPhoneNumber() : "", style);
                    createCell(row, columnCount++, adr.getEmail() != null ? adr.getEmail() : "", style);
                    createCell(row, columnCount++, adr.getLabel() != null ? adr.getLabel() : "", style);
                    createCell(row, columnCount++, adr.getLine1() != null ? adr.getLine1() : "", style);
                    if (adr.getLine2() != null)
                        createCell(sheet.createRow(rowCount++), columnCount - 1, adr.getLine2(), style);
                    createCell(row, columnCount++, adr.getProvinceName() != null ? adr.getProvinceName() : "", style);
                    createCell(row, columnCount++, adr.getDistrictName() != null ? adr.getDistrictName() : "", style);
                }
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

