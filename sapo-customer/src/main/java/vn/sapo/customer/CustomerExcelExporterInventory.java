package vn.sapo.customer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customerGroup.dto.CustomerGroupResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerExcelExporterInventory {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CustomerResult> customerList;
    @Autowired
    CustomerGroupResult customerGroupResult;

    public CustomerExcelExporterInventory(List<CustomerResult> customerList){
        this.customerList = customerList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet = workbook.createSheet("Customers");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Tên khách hàng", style);
        createCell(row, 1, "Mã khách hàng", style);
        createCell(row, 2, "Mã nhóm khách hàng", style);
        createCell(row, 3, "Áp dụng ưu đãi", style);
        createCell(row, 4, "Email", style);
        createCell(row, 5, "Điện thoại", style);
        createCell(row, 6, "Ngày sinh", style);
        createCell(row, 7, "Giới tính", style);
        createCell(row, 8, "Người liên hệ", style);
        createCell(row, 9, "Người liên hệ - SĐT", style);
        createCell(row, 10, "Người liên hệ - Email", style);
        createCell(row, 11, "Địa chỉ", style);
        createCell(row, 12, "Tỉnh thành", style);
        createCell(row, 13, "Quận huyện", style);
        createCell(row, 14, "Phường xã", style);
        createCell(row, 15, "Website", style);
        createCell(row, 16, "Fax", style);
        createCell(row, 17, "Mã số thuế", style);
        createCell(row, 18, "Mô tả", style);
        createCell(row, 19, "Chính sách giá mặc định", style);
        createCell(row, 20, "Chiết khấu mặc đinh (%)", style);
        createCell(row, 21, "Phương thức thanh toán mặc định", style);
        createCell(row, 22, "Nợ hiện tại", style);
        createCell(row, 23, "Tổng chi tiêu", style);
        createCell(row, 24, "SL đơn hàng", style);
        createCell(row, 25, "Tổng SL sản phẩm đã mua", style);
        createCell(row, 26, "Tổng SL sản phẩm hoàn trả", style);
        createCell(row, 27, "Ngày mua cuối cùng", style);
        createCell(row, 28, "Điểm hiện tại", style);
        createCell(row, 29, "Hạng thẻ hiện tại", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style){
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
        for (CustomerResult customer : customerList) {

            for (AddressResult address : customer.getAddresses()){
                String province = address.getProvinceName();
                String district = address.getDistrictName();
                String ward = address.getWardName();

                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;

                createCell(row, columnCount++, customer.getFullName(), style);
                createCell(row, columnCount++, customer.getCustomerCode(), style);
                createCell(row, columnCount++, customer.getGroup().getTitle(), style);
                createCell(row, columnCount++, "Theo nhóm khách hàng", style);
                createCell(row, columnCount++, customer.getEmail(), style);
                createCell(row, columnCount++, customer.getPhoneNumber(), style);
                createCell(row, columnCount++, customer.getBirthday().toString(), style);
                createCell(row, columnCount++, customer.getGender().getValue(), style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, address.getLine1(), style);
                createCell(row, columnCount++,province, style);
                createCell(row, columnCount++,district, style);
                createCell(row, columnCount++,ward, style);
                createCell(row, columnCount++,"website", style);
                createCell(row, columnCount++,"fax", style);
                createCell(row, columnCount++,"Mã số thuế", style);
                createCell(row, columnCount++,customer.getDescription(), style);
                createCell(row, columnCount++,"chính sách giá mặc định", style);
                createCell(row, columnCount++,"chiết khấu mặc định %", style);
                createCell(row, columnCount++,"nợ", style);
                createCell(row, columnCount++,"tổng chi", style);
                createCell(row, columnCount++,customer.getQuantityProductOrder(), style);
                createCell(row, columnCount++,customer.getQuantityItemOrder(), style);
                createCell(row, columnCount++,"tổng hoàn trả", style);
                createCell(row, columnCount++,"ngày mua cuối", style);
                createCell(row, columnCount++,"điểm hiện tại", style);
                createCell(row, columnCount++,"Hạng thẻ hiện tại", style);
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
