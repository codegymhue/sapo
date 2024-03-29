package vn.sapo.supplier.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier_group.dto.SupplierGroupResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static vn.sapo.supplier.excel.ExcelHeaderSupplier.*;

public class SupplierExcelExporterInventory {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<SupplierResult> supplierList;

    @Autowired
    SupplierGroupResult supplierGroupResult;

    public SupplierExcelExporterInventory(List<SupplierResult> supplierList) {
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
        int rowcount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (SupplierResult supplier : supplierList) {
            for (AddressResult address : supplier.getAddresses()) {
                String province = address.getProvinceName();
                String district = address.getDistrictName();
                Row row = sheet.createRow(rowcount++);
                int columnCount = 0;

                createCell(row, FULL_NAME, supplier.getFullName() != null ? supplier.getFullName() : "", style);
                createCell(row, SUPPLIER_CODE, supplier.getSupplierCode(), style);
                createCell(row, SUP_GROUP_CODE, supplier.getGroup().getSupGroupCode() , style);
                createCell(row, EMAIL, supplier.getEmail(), style);
                createCell(row, PHONE, supplier.getPhone(), style);
                createCell(row, WEBSITE, supplier.getWebsite(), style);
                createCell(row, FAX, supplier.getFax(), style);
                createCell(row, TAX_CODE, supplier.getTaxCode(), style);
                createCell(row, DESCRIPTION, supplier.getDescription(), style);
                createCell(row, PRICE_POLICY, "chính sách giá mặc định", style);
                createCell(row, PAYMENT_TERN, "kỳ hạn thanh toán mặc định", style);
                createCell(row, PAYMENT_METHOD_TITLE, supplier.getPaymentMethod(), style);
                createCell(row, CONTACT_NAME, address.getFullName(), style);
                createCell(row, CONTACT_PHONE, address.getPhoneNumber(), style);
                createCell(row, CONTACT_EMAIL, address.getEmail(), style);
                createCell(row, LABEL, address.getLabel(), style);
                createCell(row, LINE1, address.getLine1(), style);
                createCell(row, LINE2, address.getLine2(), style);
                createCell(row, PROVINCE_NAME, province, style);
                createCell(row, DISTRICT_NAME, district, style);
                createCell(row, CURRENT_DEBT, "nợ hiện tại", style);
                createCell(row, TAGS, supplier.getTags(), style);

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