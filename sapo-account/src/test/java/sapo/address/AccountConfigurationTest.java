package sapo.address;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import vn.sapo.account.AccountService;
import vn.sapo.account.AccountServiceImpl;
import vn.sapo.account.dto.AccountMapper;


@TestConfiguration
public class AccountConfigurationTest {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    AccountService addressService() {
        return new AccountServiceImpl();
    }

    @Bean
    AccountMapper addressMapper() {
        return new AccountMapper();
    }
}
