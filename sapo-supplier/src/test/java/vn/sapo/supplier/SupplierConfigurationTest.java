package vn.sapo.supplier;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SupplierConfigurationTest {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }
    @Bean
    SupplierService supplierService () {
        return new SupplierServiceImpl();
    }

    @Bean
    SupplierMapper supplierMapper() {
        return new SupplierMapper();
    }
}
