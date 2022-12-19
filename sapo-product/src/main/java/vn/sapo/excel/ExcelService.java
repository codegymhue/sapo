package vn.sapo.excel;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.entities.product.Product;
import vn.sapo.product.ProductRepository;


@Service
public class ExcelService {
    @Autowired
    ProductRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Product> products = ExcelHelper.excelToProducts(file.getInputStream());
            repository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}