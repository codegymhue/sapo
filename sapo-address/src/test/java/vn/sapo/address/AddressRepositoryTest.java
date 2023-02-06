package vn.sapo.address;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import vn.sapo.entities.Address;
import vn.sapo.shared.exceptions.NotFoundException;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/sapo.sql")
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testFindAllByCustomerId() {
        List<Address> addressList = addressRepository.findAllByCustomerId(385);
        Assertions.assertThat(addressList).hasSize(1);
        try {
            addressRepository.findAllByCustomerId(386);
        } catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "customer not found");
        }
    }

    @Test
    public void testFindAll() {
        Assertions.assertThat(addressRepository.findAll()).hasSize(1);
    }
    @Test
    public void testFindById(){
        Assertions.assertThat(addressRepository.findById(1).get().getId()).isEqualTo(1);
        try{
            addressRepository.findById(2);
        }catch (Exception e ) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "address not found");
        }
    }

    @Test
    public void testExistsById() {
        Assertions.assertThat(addressRepository.existsById(1)).isTrue();
        try {
           addressRepository.existsById(2);
        }catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "address not found");
        }
    }

}
