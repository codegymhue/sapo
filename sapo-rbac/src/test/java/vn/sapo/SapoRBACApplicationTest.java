package vn.sapo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("vn.sapo.entities")
public class SapoRBACApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SapoRBACApplicationTest.class, args);
    }

}