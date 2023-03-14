package vn.sapo.customer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import vn.sapo.customer.dto.CustomerParamExport;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.customer.dto.CustomerResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class CustomerExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private CustomerParamExport customerParamExport;
    private List<CustomerResult> customerList;
    private  HashMap<String, String> listColumn;


    public CustomerExcelExporter(CustomerParamExport customerParamExport, List<CustomerResult> customerList) {
        this.customerList = customerList;
        this.customerParamExport = customerParamExport;
        this.listColumn = new HashMap<>();
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


        listColumn.put("fullName", "Tên khách hàng");
        listColumn.put("customerCode", "Mã khách hàng");
        listColumn.put("customerGroupCode", "Mã nhóm khách hàng");
        listColumn.put("endow", "Áp dụng ưu đãi");
        listColumn.put("email", "Email");
        listColumn.put("phoneNumber", "Điện thoại");
        listColumn.put("birthDay", "Ngày sinh");
        listColumn.put("gender", "Giới tính");
        listColumn.put("contact", "Người liên hệ");
        listColumn.put("contactPhone", "Người liên hệ - SĐT");
        listColumn.put("contactEmail", "Người liên hệ - Email");
        listColumn.put("address", "Địa chỉ");
        listColumn.put("province", "Tỉnh thành");
        listColumn.put("district", "Quận huyện");
        listColumn.put("ward", "Phường xã");
        listColumn.put("website", "Website");
        listColumn.put("fax", "Fax");
        listColumn.put("taxCode", "Tax");
        listColumn.put("description", "Description");
        listColumn.put("policy", "Chính sách giá mặc định");
        listColumn.put("discount", "Chính sách giá mặc định");
        listColumn.put("paymentMethod", "Phương thức thanh toán mặc định");
        listColumn.put("debtTotal", "Nợ hiện tại");
        listColumn.put("spendTotal", "Tổng chi tiêu");
        listColumn.put("quantityProductOrder", "SL đơn hàng");
        listColumn.put("quantityItemOrder", "Tổng SL sản phẩm đã mua");
        listColumn.put("returnsTotal", "Tổng SL sản phẩm hoàn trả");
        listColumn.put("lastDayOrder", "Ngày mua cuối cùng");
        for(int i=0; i<customerParamExport.getListNameColumn().size(); i++) {
            String nameColumn = customerParamExport.getListNameColumn().get(i);
            createCell(row, i, listColumn.get(nameColumn), style);
        }
//        createCell(row, 0, "Tên khách hàng", style);
//        createCell(row, 1, "Mã khách hàng", style);
//        createCell(row, 2, "Mã nhóm khách hàng", style);
//        createCell(row, 3, "Áp dụng ưu đãi", style);
//        createCell(row, 4, "Email", style);
//        createCell(row, 5, "Điện thoại", style);
//        createCell(row, 6, "Ngày sinh", style);
//        createCell(row, 7, "Giới tính", style);
//        createCell(row, 8, "Người liên hệ", style);
//        createCell(row, 9, "Người liên hệ - SĐT", style);
//        createCell(row, 10, "Người liên hệ - Email", style);
//        createCell(row, 11, "Địa chỉ", style);
//        createCell(row, 12, "Tỉnh thành", style);
//        createCell(row, 13, "Quận huyện", style);
//        createCell(row, 14, "Phường xã", style);
//        createCell(row, 15, "Website", style);
//        createCell(row, 16, "Fax", style);
//        createCell(row, 17, "Mã số thuế", style);
//        createCell(row, 18, "Mô tả", style);
//        createCell(row, 19, "Chính sách giá mặc định", style);
//        createCell(row, 20, "Chiết khấu mặc đinh (%)", style);
//        createCell(row, 21, "Phương thức thanh toán mặc định", style);
//        createCell(row, 22, "Nợ hiện tại", style);
//        createCell(row, 23, "Tổng chi tiêu", style);
//        createCell(row, 24, "SL đơn hàng", style);
//        createCell(row, 25, "Tổng SL sản phẩm đã mua", style);
//        createCell(row, 26, "Tổng SL sản phẩm hoàn trả", style);
//        createCell(row, 27, "Ngày mua cuối cùng", style);
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


    private void writeDataLines1() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (CustomerResult customer : customerList) {

            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            List<String> listColumnExist = customerParamExport.getListNameColumn();
                if(listColumnExist.contains("fullName"))
                    createCell(row, columnCount++, customer.getFullName() != null ? customer.getFullName() : "" , style);
                if(listColumnExist.contains("customerCode"))
                    createCell(row, columnCount++, customer.getCustomerCode(), style);
                if(listColumnExist.contains("customerGroupCode"))
                    createCell(row, columnCount++, customer.getGroup().getTitle(), style);
                if(listColumnExist.contains("endow"))
                    createCell(row, columnCount++, customer.getGroup().getTitle(), style);
                if(listColumnExist.contains("email"))
                    createCell(row, columnCount++, customer.getEmail() != null ? customer.getEmail() : "", style);
                if(listColumnExist.contains("phoneNumber"))
                    createCell(row, columnCount++, customer.getPhoneNumber() != null ? customer.getPhoneNumber() : "" , style);
                if(listColumnExist.contains("birthDay"))
                    createCell(row, columnCount++, customer.getBirthday() != null ? customer.getBirthday().toString() : "", style);
                if(listColumnExist.contains("gender"))
                    switch (customer.getGender().getValue()) {
                        case "NAM":
                            createCell(row, columnCount++, nam, style);
                            break;
                        case "NU":
                            createCell(row, columnCount++, nu, style);
                            break;
                        case "KHAC":
                            createCell(row, columnCount++, khac, style);
                            break;
                    }
                if (customer.getAddresses().size() == 0) {
                    if(listColumnExist.contains("contact"))
                        createCell(row, columnCount++, "", style);
                    if(listColumnExist.contains("contactPhone"))
                        createCell(row, columnCount++, "", style);
                    if(listColumnExist.contains("contactEmail"))
                        createCell(row, columnCount++, "", style);
                    if(listColumnExist.contains("address"))
                        createCell(row, columnCount++, "", style);
                    //LINE2 === ?
//                        createCell(row, columnCount++, "", style);
                    if(listColumnExist.contains("province"))
                        createCell(row, columnCount++, "", style);
                    if(listColumnExist.contains("district"))
                        createCell(row, columnCount++, "", style);
                    if(listColumnExist.contains("ward"))
                        createCell(row, columnCount++, "", style);
                }else {
                    for (AddressResult ar : customer.getAddresses()) {
                        if(listColumnExist.contains("contact"))
                            createCell(row, columnCount++, ar.getFullName() != null ? ar.getFullName() : "", style);
                        if(listColumnExist.contains("contactPhone"))
                            createCell(row, columnCount++, ar.getPhoneNumber() != null ? ar.getPhoneNumber() : "", style);
                        if(listColumnExist.contains("contactEmail"))
                            createCell(row, columnCount++, ar.getEmail() != null ? ar.getEmail() : "", style);
                        if(listColumnExist.contains("address"))
                            createCell(row, columnCount++, ar.getLine1() != null ? ar.getLine1() : "", style);
                        if (ar.getLine2() != null)
                            createCell(sheet.createRow(rowCount++),columnCount-1,ar.getLine2(),style);
                        if(listColumnExist.contains("province"))
                            createCell(row, columnCount++, ar.getProvinceName() != null ?  ar.getProvinceName() : "", style);
                        if(listColumnExist.contains("district"))
                            createCell(row, columnCount++, ar.getDistrictName() != null ? ar.getDistrictName() : "", style);
                        if(listColumnExist.contains("ward"))
                            createCell(row, columnCount++, ar.getWardName() != null ? ar.getWardName() : "", style);
                    }
                }
                if(listColumnExist.contains("website"))
                    createCell(row, columnCount++, customer.getWebsite()!=null ? customer.getWebsite() : "", style);
                if(listColumnExist.contains("fax"))
                    createCell(row, columnCount++, customer.getFax() !=null ? customer.getFax() : "", style);
                if(listColumnExist.contains("taxCode"))
                    createCell(row, columnCount++, customer.getTaxCode() !=null ? customer.getTaxCode() : "", style);
                if(listColumnExist.contains("description"))
                    createCell(row, columnCount++, customer.getDescription() != null ? customer.getDescription() : "" , style);
                if(listColumnExist.contains("policy"))
                    createCell(row, columnCount++, "Theo nhóm " +"' " + customer.getGroup().getTitle() +" '", style);
                if(listColumnExist.contains("discount"))
                    createCell(row, columnCount++, customer.getGroup().getDiscount(), style);
                if(listColumnExist.contains("paymentMethod"))
                    createCell(row, columnCount++, "", style);
                if(listColumnExist.contains("debtTotal"))
                    createCell(row, columnCount++, customer.getDebtTotal() != null ? customer.getDebtTotal() : "0" , style);
                if(listColumnExist.contains("spendTotal"))
                    createCell(row, columnCount++, customer.getSpendTotal() != null ? customer.getSpendTotal() : "0", style);
                if(listColumnExist.contains("quantityItemOrder"))
                    createCell(row, columnCount++, customer.getQuantityItemOrder(), style);
                if(listColumnExist.contains("quantityProductOrder"))
                    createCell(row, columnCount++, customer.getQuantityProductOrder(), style);
                if(listColumnExist.contains("returnsTotal"))
                    createCell(row, columnCount++, "", style);
                if(listColumnExist.contains("lastDayOrder"))
                    createCell(row, columnCount++, customer.getLastDayOrder()!=null ? customer.getLastDayOrder().toString() : "", style);
        }
}

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines1();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
