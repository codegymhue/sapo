package vn.sapo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:versions.properties")
@EntityScan("vn.sapo.entities")
//@ComponentScan("vn.sapo")
public class SapoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SapoApplication.class, args);
    }

}