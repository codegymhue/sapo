package vn.sapo.address;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AddressConfigurationTest {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    AddressService addressService() {
        return new AddressServiceImpl();
    }

    @Bean
    AddressMapper addressMapper() {
        return new AddressMapper();
    }
}
