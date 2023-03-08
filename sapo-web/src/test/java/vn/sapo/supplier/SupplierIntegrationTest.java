package vn.sapo.supplier;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.sapo.controllers.supplier.SupplierAPI;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

@SpringBootTest
public class SupplierIntegrationTest {

    @Autowired
    SupplierAPI supplierAPI;

    private SupplierResult expected;

    @BeforeEach
    public void setUp() {
        UpdateSupplierParam supplierParam = (UpdateSupplierParam) new UpdateSupplierParam()
                .setId(98)
                .setSupplierCode("SPL0005")
                .setFullName("Thanh Ngân")
                .setEmail("thanhngan1002@gmail.com")
                .setPhone("0989876565")
                .setDescription("Quen thuoc")
                .setGroupId(99);
//        expected = (SupplierResult) supplierAPI.update(supplierParam).getBody();
    }

    @Test
    public void testFindById() {
        SupplierResult actual = supplierAPI.findById(expected.getId()).getBody();
        assertSupplier(actual, expected);
    }

    public void assertSupplier(SupplierResult actual, SupplierResult expected) {
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getEmployee().getId()).isEqualTo(expected.getEmployee().getId());
        Assertions.assertThat(actual.getPaymentMethod().getId()).isEqualTo(expected.getPaymentMethod().getId());
        Assertions.assertThat(actual.getSupplierCode()).isEqualTo(expected.getSupplierCode());
        Assertions.assertThat(actual.getCreatedAt()).isEqualTo(expected.getCreatedAt());
        Assertions.assertThat(actual.getUpdatedAt()).isEqualTo(expected.getUpdatedAt());
    }
}
