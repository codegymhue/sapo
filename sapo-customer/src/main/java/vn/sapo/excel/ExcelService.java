package vn.sapo.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.entities.customer.Customer;

import java.io.IOException;
import java.util.List;

public class ExcelService {
    @Autowired
    CustomerService customerService;

    public void save(MultipartFile file) {
        try {
            List<CreateCustomerParam> products = ExcelHelper.excelToCustomers(file.getInputStream());
            for (CreateCustomerParam p : products) {
                customerService.create(p);
            }

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}