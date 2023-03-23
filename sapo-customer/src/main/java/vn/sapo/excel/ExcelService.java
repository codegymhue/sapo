package vn.sapo.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CreateCustomerParam;

import java.io.IOException;
import java.util.List;
@Component
public class ExcelService {
    public List<CreateCustomerParam> save(MultipartFile file) {
        try {
            List<CreateCustomerParam> customers = ExcelHelper.excelToCustomers(file.getInputStream());
            return customers;

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}