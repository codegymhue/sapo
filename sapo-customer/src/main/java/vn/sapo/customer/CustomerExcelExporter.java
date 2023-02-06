package vn.sapo.customer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.customer.dto.CustomerResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class CustomerExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CustomerResult> customerList;


    public CustomerExcelExporter(List<CustomerResult> customerList) {
        this.customerList = customerList;
        workbook = new XSSFWorkbook();
    }

    String nam = "Nam";
    String nu = "Nữ";
    String khac = "Khác";


    private void writeHeaderLine() {
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

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        else if (value instanceof Boolean) {
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

        for (CustomerResult customer : customerList) {

            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
                createCell(row, columnCount++, customer.getFullName() != null ? customer.getFullName() : "" , style);
                createCell(row, columnCount++, customer.getCustomerCode(), style);
                createCell(row, columnCount++, customer.getGroup().getTitle(), style);
                createCell(row, columnCount++, customer.getGroup().getTitle(), style);
                createCell(row, columnCount++, customer.getEmail() != null ? customer.getEmail() : "", style);
                createCell(row, columnCount++, customer.getPhoneNumber() != null ? customer.getPhoneNumber() : "" , style);
                createCell(row, columnCount++, customer.getBirthday() != null ? customer.getBirthday().toString() : "", style);


                if (customer.getGender().getValue().equals("NAM"))
                    createCell(row, columnCount++, nam, style);
                if (customer.getGender().getValue().equals("NU"))
                    createCell(row, columnCount++, nu, style);
                if (customer.getGender().getValue().equals("KHAC"))
                    createCell(row, columnCount++, khac, style);

                if (customer.getAddresses().size() == 0) {
                    createCell(row, columnCount++, "", style);
                    createCell(row, columnCount++, "", style);
                    createCell(row, columnCount++, "", style);
                    createCell(row, columnCount++, "", style);
                    createCell(row, columnCount++, "", style);
                    createCell(row, columnCount++, "", style);
                    createCell(row, columnCount++, "", style);
                }else {
                    for (AddressResult ar : customer.getAddresses()) {
                        createCell(row, columnCount++, ar.getFullName() != null ? ar.getFullName() : "", style);
                        createCell(row, columnCount++, ar.getPhoneNumber() != null ? ar.getPhoneNumber() : "", style);
                        createCell(row, columnCount++, ar.getEmail() != null ? ar.getEmail() : "", style);
                        createCell(row, columnCount++, ar.getLine1() != null ? ar.getLine1() : "", style);
                        if (ar.getLine2() != null)
                            createCell(sheet.createRow(rowCount++),columnCount-1,ar.getLine2(),style);
                        createCell(row, columnCount++, ar.getProvinceName() != null ?  ar.getProvinceName() : "", style);
                        createCell(row, columnCount++, ar.getDistrictName() != null ? ar.getDistrictName() : "", style);
                        createCell(row, columnCount++, ar.getWardName() != null ? ar.getWardName() : "", style);
                    }
                }
                createCell(row, columnCount++, customer.getWebsite()!=null ? customer.getWebsite() : "", style);
                createCell(row, columnCount++, customer.getFax() !=null ? customer.getFax() : "", style);
                createCell(row, columnCount++, customer.getTaxCode() !=null ? customer.getTaxCode() : "", style);
                createCell(row, columnCount++, customer.getDescription() != null ? customer.getDescription() : "" , style);
                createCell(row, columnCount++, "Theo nhóm " +"' " + customer.getGroup().getTitle() +" '", style);
                createCell(row, columnCount++, customer.getGroup().getDiscount(), style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, customer.getDebtTotal() != null ? customer.getDebtTotal() : "0" , style);
                createCell(row, columnCount++, customer.getSpendTotal() != null ? customer.getSpendTotal() : "0", style);
                createCell(row, columnCount++, customer.getQuantityItemOrder(), style);
                createCell(row, columnCount++, customer.getQuantityProductOrder(), style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++, customer.getLastDayOrder()!=null ? customer.getLastDayOrder().toString() : "", style);
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
