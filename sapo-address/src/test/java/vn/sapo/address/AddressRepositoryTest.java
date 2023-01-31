package vn.sapo.address;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.jdbc.Sql;
import vn.sapo.address.dto.UpdateAddressParam;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/sapo.sql")
public class AddressRepositoryTest {
    //        @Spy
    @Autowired
    private AddressRepository addressRepository;


    //        @Test
//        public void testFindAllByCustomerId() {
//
//        }
    @Test
    public void testFindAll() {
        Assertions.assertThat(addressRepository.findAll()).hasSize(1);
    }


}
