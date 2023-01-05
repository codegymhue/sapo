package vn.sapo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.customer.dto.CreateCustomerParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADER = { "Id","Customer Code", "Full Name", "Phone Number", "Group id",
            "Email", "Birthday", "Gender", "employeeId"};
    static String SHEET = "Customer";

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
//                List<ProductTaxParam> taxParams = new ArrayList<>();
//                ProductTaxParam taxIn = new ProductTaxParam();
//                ProductTaxParam taxOut = new ProductTaxParam();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
//                            customer.setId((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
//                            product.setTitle(currentCell.getStringCellValue());
                            break;
                        case 2:
//                            product.setSku(currentCell.getStringCellValue());
                            break;
                        case 3:
//                            product.setMass((float) currentCell.getNumericCellValue());
                            break;
                        case 4:
//                            product.setBarCode(currentCell.getStringCellValue());
                            break;
                        case 5:
//                            product.setUnit(currentCell.getStringCellValue());
                            break;
                        case 6:
//                            product.setRetailPrice(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 7:
//                            product.setWholesalePrice(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 8:
//                            product.setImportPrice(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 9:
//                            product.setBrandId((int) currentCell.getNumericCellValue());
                            break;
                        case 10:
//                            product.setCategoryId((int) currentCell.getNumericCellValue());
                            break;
                        case 11:
//                            product.setApplyTax(currentCell.getBooleanCellValue());
                            break;
                        case 12:
//                            product.setTaxInclusive(currentCell.getBooleanCellValue());
                            break;
                        case 13:
//                            taxIn.setTaxId((int) currentCell.getNumericCellValue());
                            break;
                        case 14:
//                            taxOut.setTaxId((int) currentCell.getNumericCellValue());
                            break;
                        case 15:
//                            taxIn.setTaxType(TaxType.parseTypeTax(currentCell.getStringCellValue()));
                            break;
                        case 16:
//                            taxOut.setTaxType(TaxType.parseTypeTax(currentCell.getStringCellValue()));
                            break;
                        case 17:
//                            product.setDescription(currentCell.getStringCellValue());
                            break;
                        case 18:
//                            product.setEnableSell(currentCell.getBooleanCellValue());
                            break;
                        case 19:
//                            product.setEnableVariant(currentCell.getBooleanCellValue());
                            break;
                        case 20:
//                            product.setCostPrice(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 21:
//                            product.setQuantity((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                customers.add(customer);
            }

            workbook.close();

            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
