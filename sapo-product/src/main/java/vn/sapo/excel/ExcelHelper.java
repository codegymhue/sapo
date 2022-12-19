package vn.sapo.excel;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.product.ProductStatus;


public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADERs = { "Title", "SKU", "Mass", "BarCode", "Unit", "Retail Price", "Wholesale Price", "Import Price", "Brand", "Category", "Status", "Apply Tax", "Tax Inclusive"};
    static String SHEET = "Products";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Product> excelToProducts(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Product> products = new ArrayList<Product>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Product product = new Product();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            product.setId((int) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            product.setTitle(currentCell.getStringCellValue());
                            break;

                        case 2:
                            product.setSku(currentCell.getStringCellValue());
                            break;
                        case 3:
                            product.setMass((float) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            product.setBarCode(currentCell.getStringCellValue());
                            break;
                        case 5:
                            product.setUnit(currentCell.getStringCellValue());
                            break;
                        case 6:
                            product.setRetailPrice(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 7:

                            product.setWholesalePrice(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 8:
                            product.setImportPrice(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 9:
                            product.setBrandId((int) currentCell.getNumericCellValue());
                            break;
                        case 10:
                            product.setCategoryId((int) currentCell.getNumericCellValue());
                            break;
                        case 11:
                            product.setStatus(ProductStatus.parseProductStatus(currentCell.getStringCellValue()));
                            break;
                        case 12:
                            product.setApplyTax(currentCell.getBooleanCellValue());
                            break;
                        case 13:
                            product.setTaxInclusive(currentCell.getBooleanCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                products.add(product);
            }

            workbook.close();

            return products;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}