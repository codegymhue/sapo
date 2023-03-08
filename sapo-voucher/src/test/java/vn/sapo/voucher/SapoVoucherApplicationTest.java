package vn.sapo.voucher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("vn.sapo.entities")
public class SapoVoucherApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SapoVoucherApplicationTest.class, args);
    }

}