package vn.sapo.supplier;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.supplier.SupplierRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Sql("/sapo.sql")
public class SupplierRepositoryTest {
    @Autowired
    SupplierRepository supplierRepository;

    @Test
    public void testFindAll() {
        Assertions.assertThat(supplierRepository.findAll()).hasSize(3);
    }

    @Test
    public void testFindById() {
        Assertions.assertThat(supplierRepository.findById(1).get().getId()).isEqualTo(1);
        try {
            supplierRepository.findById(7);
        }catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "Not found supplier with id: 7");
        }
    }
    @Test
    public void testCreate() {
        Supplier supplier = new Supplier()
                .setId(1)
                .setName("Thanh Hoan")
                .setEmail("thanhhoan45@gmail.com")
                .setPhone("0989876765");
        Supplier actual = supplierRepository.save(supplier);
        Supplier expected = supplierRepository.findById(1).get();
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void testDeleteById() {

    }
}
