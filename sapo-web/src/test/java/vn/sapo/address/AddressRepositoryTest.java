package vn.sapo.address;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/address.sql")
public class AddressRepositoryTest {
//        @Spy
//        private AddressRepository addressRepository;
////        @Test
////        public void testFindAllByCustomerId() {
////
////        }
        @Test
        public void testFindAll() {
//                Assertions.assertThat(addressRepository.findAll()).hasSize(1);
        }


}
