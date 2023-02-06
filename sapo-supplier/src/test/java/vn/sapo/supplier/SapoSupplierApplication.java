package vn.sapo.supplier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("vn.sapo.entities")
public class SapoSupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(SapoSupplierApplication.class, args);
    }

}
