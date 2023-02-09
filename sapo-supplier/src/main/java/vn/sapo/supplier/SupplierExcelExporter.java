//
//package vn.sapo.supplier;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import vn.sapo.entities.supplier.Supplier;
//import vn.sapo.supplier.dto.SupplierResult;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import static org.apache.poi.ss.util.CellUtil.createCell;
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
//        font.setFontHeight(16);
//        style.setFont(font);
//
//        createCell(row, 0, "Tên nhà cung cấp ", style);
//        createCell(row, 1, "Mã nhà cung cấp", style);
//        createCell(row, 2, "Mã nhóm nhà cung cấp", style);
//        createCell(row, 3, "Email", style);
//        createCell(row, 4, "Điện thoại", style);
//        createCell(row, 5, "Website", style);
//        createCell(row, 6, "FAX", style);
//        createCell(row, 7, "Mã số thuế", style);
//        createCell(row, 8, "Mô tả", style);
//        createCell(row, 9, "Chính sách giá mặc định", style);
//        createCell(row, 10, "Thuế mặc định", style);
//        createCell(row, 11, "Kỳ hạn thanh toán mặc định", style);
//        createCell(row, 12, "Phương thức thanh toán mặc định", style);
//        createCell(row, 13, "Người liên hệ ", style);
//        createCell(row, 14, "SDT người liên hệ ", style);
//        createCell(row, 15, "Email người liên hệ", style);
//        createCell(row, 16, "Nhãn", style);
//        createCell(row, 17, "Địa chỉ 1", style);
//        createCell(row, 18, "Địa chỉ 2", style);
//        createCell(row, 19, "Tỉnh/Thành Phố", style);
//        createCell(row, 20, "Quận/Huyện", style);
//        createCell(row, 21, "Nợ hiện tại", style);
//        createCell(row, 21, "Tags", style);
//    }
//
//    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
//        sheet.autoSizeColumn(columnCount);
//        Cell cell = row.createCell(columnCount);
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
//}
