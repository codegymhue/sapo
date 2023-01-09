package vn.sapo.product;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import vn.sapo.product.dto.ProductResult;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ProductExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ProductResult> productList;

    public ProductExcelExporter(List<ProductResult> productList) {
        this.productList = productList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Tên sản phẩm", style);
        createCell(row, 2, " Loại", style);
        createCell(row, 3, "Nhãn hiệu", style);
        createCell(row, 4, "Trạng thái", style);
        createCell(row, 5, "Giá bán lẻ", style);
        createCell(row, 6, "Giá bán buôn", style);
        createCell(row, 7, "Giá nhập", style);
        createCell(row, 8, "Đơn vị", style);
        createCell(row, 9, "Mã SKU", style);
        createCell(row, 10, "Mã BarCode", style);
        createCell(row, 11, "Khối Lượng", style);
        createCell(row, 12, "Mô tả", style);
        createCell(row, 13, "Ngày khởi tạo", style);
        createCell(row, 14, "Ngày cập nhật", style);
        createCell(row, 15, "Có thể bán", style);
        createCell(row, 16, "Tồn kho", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Long){
            cell.setCellValue((Long) value);
        }
        else {
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

        for (ProductResult product : productList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, product.getId(), style);
            createCell(row, columnCount++, product.getTitle(), style);
            createCell(row, columnCount++, product.getCategory().toString(), style);
            createCell(row, columnCount++, product.getBrand().toString(), style);
            createCell(row, columnCount++, product.getStatus().toString() == "AVAILABLE" ? "Đang giao dịch" : "Ngừng giao dịch", style);
            createCell(row, columnCount++, product.getRetailPrice().toString(), style);
            createCell(row, columnCount++, product.getWholesalePrice().toString(), style);
            createCell(row, columnCount++, product.getImportPrice().toString(), style);
            createCell(row, columnCount++, product.getUnit(), style);
            createCell(row, columnCount++, product.getSku(), style);
            createCell(row, columnCount++, product.getBarCode(), style);
            createCell(row, columnCount++, product.getMass().toString(), style);
            createCell(row, columnCount++, product.getDescription(), style);
            createCell(row, columnCount++, product.getCreatedAt().toString(), style);
            createCell(row, columnCount++, product.getUpdatedAt().toString(), style);
            createCell(row, columnCount++, product.getAvailableInventory(), style);
            createCell(row, columnCount++, product.getTotalInventory(), style);
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