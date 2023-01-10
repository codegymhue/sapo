package vn.sapo.excel;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.product.ProductService;
import vn.sapo.product.dto.CreateProductParam;


@Service
public class ExcelService {
    @Autowired
    ProductService productService;

    public void save(MultipartFile file) {
        try {
            List<CreateProductParam> products = ExcelHelper.excelToProducts(file.getInputStream());
            for (CreateProductParam p : products) {
                productService.create(p);
            }

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}