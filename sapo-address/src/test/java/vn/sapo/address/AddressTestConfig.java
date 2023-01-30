package vn.sapo.address;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressTestConfig {
    @Bean
    public AddressRepository productRepository() {
        return Mockito.mock(AddressRepository.class);
    }
}
