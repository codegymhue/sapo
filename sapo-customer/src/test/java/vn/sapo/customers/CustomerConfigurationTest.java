package vn.sapo.customers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import vn.sapo.customer.CustomerMapper;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.CustomerServiceImpl;

@TestConfiguration
public class CustomerConfigurationTest {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    CustomerService customerService() {
        return new CustomerServiceImpl();
    }

    @Bean
    CustomerMapper cusotmerMapper() {
        return new CustomerMapper();
    }
}
