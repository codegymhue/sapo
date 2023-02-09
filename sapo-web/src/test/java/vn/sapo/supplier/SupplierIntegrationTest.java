package vn.sapo.supplier;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.controllers.supplier.SupplierAPI;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;

@SpringBootTest
public class SupplierIntegrationTest {
    @Autowired
    SupplierAPI supplierAPI;

    private SupplierResult expected;

    
    @BeforeEach
    public void setUp() {
        CreateSupplierParam supplierParam = new CreateSupplierParam()
                .setName("Thanh Ngân")
                .setEmail("thanhngan1002@gmail.com")
                .setPhone("0989876565")
                .setDescription("Quen thuoc")
                .setSupplierCode("SPL0005");
//        expected = supplierAPI.save(supplierParam).getBody();
    }

    @Test
    public void testFindById() {
        SupplierResult actual = supplierAPI.findById(expected.getId()).getBody();
        assertSupplier(actual, expected);
    }

    public void assertSupplier(SupplierResult actual, SupplierResult expected) {
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getEmployeeId()).isEqualTo(expected.getEmployeeId());
        Assertions.assertThat(actual.getEmployee()).isEqualTo(expected.getEmployee());
        Assertions.assertThat(actual.getPaymentMethodId()).isEqualTo(expected.getPaymentMethodId());
        Assertions.assertThat(actual.getPaymentMethod()).isEqualTo(expected.getPaymentMethod());
        Assertions.assertThat(actual.getSupplierCode()).isEqualTo(expected.getSupplierCode());
        Assertions.assertThat(actual.getCreateAt()).isEqualTo(expected.getCreateAt());
        Assertions.assertThat(actual.getUpdateAt()).isEqualTo(expected.getUpdateAt());
    }
}
