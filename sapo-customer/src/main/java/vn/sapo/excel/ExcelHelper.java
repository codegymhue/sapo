package vn.sapo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.CustomerServiceImpl;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGender;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADER = {"Id", "Customer Code", "Full Name", "Phone Number", "Group id",
            "Email", "Birthday", "Gender", "employeeId"};
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
            int id = 500;
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
                AddressResult address = new AddressResult();


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
                            customer.setCustomerCode(currentCell.getStringCellValue());
                            break;
                        case 2:
                            customer.setGroupId((int) currentCell.getNumericCellValue());
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
//                             Website
                            break;
                        case 9:
//                             Fax
                            break;
                        case 10:
//                           Mã số thuế
                            break;
                        case 11:
//                            SĐT nhân viên phụ trách
                            break;
                        case 12:
                            customer.setDescription(currentCell.getStringCellValue());
                            break;
                        case 13:
//                            chính sách giá mặc định
                            break;
                        case 14:
//                            chiết khấu mặc định
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
//                            nợ hiện tại
                            break;
                        case 24:
//                            tổng chi tiêu
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



                customer.setAddresses(new ArrayList<AddressResult>() {{
                    add(address);
                }});
                customer.setId(id);
                address.setId(id);
                id++;
                customers.add(customer);

            }

            workbook.close();



            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
