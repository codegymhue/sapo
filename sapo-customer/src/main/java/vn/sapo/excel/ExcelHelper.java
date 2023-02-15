package vn.sapo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.CustomerGender;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADER = {"Tên khách hàng", "Mã khách hàng", "Mã nhóm khách hàng", "Áp dụng ưu đãi", "Email",
            "Điện thoại", "Ngày sinh", "Giới tính", "Website", "Fax", "Mã số thuế", "SĐT nhân viên phụ trách",
            "Mô tả", "Chính sách giá mặc định", "Chiết khấu mặc định (%)", "Phương thức thanh toán mặc định",
            "Người liên hệ", "Người liên hệ - SĐT", "Người liên hệ - Email", "Địa chỉ", "Tỉnh thành", "Quận huyện",
            "Phường xã", "Nợ hiện tại", "Tổng chi tiêu", "Ghi chú", "Tags" };

    static String SHEET = "FileNhapDSKhachHang";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<CreateCustomerParam> excelToCustomers(InputStream is) {

        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<CreateCustomerParam> customers = new ArrayList<CreateCustomerParam>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                CreateCustomerParam customer = new CreateCustomerParam();
                customer.setEmployeeId(6);

                CreateAddressParam address = new CreateAddressParam();
                address.setProvinceId(-1);
                address.setDistrictId(-1);
                address.setWardId(-1);


                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            customer.setFullName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            if (currentCell.getStringCellValue() == null)
                                customer.setCustomerCode("CUZN000" + customer.getId());
                            customer.setCustomerCode(currentCell.getStringCellValue());
                            break;
                        case 2:
                            System.out.println(customer.setGroupId((int) currentCell.getNumericCellValue()));
                            customer.setGroupId((int) currentCell.getNumericCellValue());
//
                            break;
                        case 3:
//                             áp dụng uu đãi
                            break;
                        case 4:
                            customer.setEmail(currentCell.getStringCellValue());
                            break;
                        case 5:
                            customer.setPhoneNumber(currentCell.getStringCellValue());
                            break;
                        case 6:
                            customer.setBirthday(currentCell.getDateCellValue());
                            break;
                        case 7:
                            customer.setGender(CustomerGender.parseCustomerGender(currentCell.getStringCellValue()));
                            break;
                        case 8:
                            customer.setWebsite(currentCell.getStringCellValue());
                            break;
                        case 9:
                             customer.setFax(currentCell.getStringCellValue());
                            break;
                        case 10:
                            customer.setTaxCode(currentCell.getStringCellValue());
                            break;
                        case 11:

                            break;
                        case 12:
                            customer.setDescription(currentCell.getStringCellValue());
                            break;
                        case 13:
//                            chính sách giá mặc định
                            break;
                        case 14:
                            customer.setGroup(new CustomerGroupResult().setDiscount(0));
                            break;
                        case 15:
//                            phương thức thanh toán mặc định
                            break;
                        case 16:
                            address.setFullName(currentCell.getStringCellValue());
                            break;
                        case 17:
                            address.setPhoneNumber(currentCell.getStringCellValue());
                            break;
                        case 18:
                            address.setEmail(currentCell.getStringCellValue());
                            break;
                        case 19:
                            address.setLine1(currentCell.getStringCellValue());
                            break;
                        case 20:
                            address.setProvinceName(currentCell.getStringCellValue());
                            break;
                        case 21:
                            address.setDistrictName(currentCell.getStringCellValue());
                            break;
                        case 22:
                            address.setWardName(currentCell.getStringCellValue());
                            break;
                        case 23:
                            customer.setDebtTotal(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 24:
                            customer.setSpendTotal(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 25:
//                            gi chú
                            break;
                        case 26:
//                            tag
                            break;
                        default:
                            break;
                    }
                    cellIdx++;

                }

                customer.setCreateAddressParam(address);
                customers.add(customer);
            }
            workbook.close();

            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
