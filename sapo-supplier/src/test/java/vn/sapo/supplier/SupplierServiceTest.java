package vn.sapo.supplier;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.supplier.SupplierConfigurationTest;
import vn.sapo.supplier.SupplierMapper;
import vn.sapo.supplier.SupplierRepository;
import vn.sapo.supplier.SupplierService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(SupplierConfigurationTest.class)
public class SupplierServiceTest {
    @MockBean
    SupplierRepository supplierRepository;

    @Autowired
    SupplierMapper supplierMapper;

    @Autowired
    SupplierService supplierService;

    private static final List<Supplier> suppliers = new ArrayList<>();
    @BeforeAll
    public static void setUp() {
        suppliers.add(new Supplier()
                .setId(1)
                .setFullName("Thanh Ngân")
                .setEmail("thanhngan1002@gmail.com")
                .setPhone("0989876565")
                .setDescription("Quen thuoc")
                .setSupplierCode("SPL0001")
                .setStatus(SupplierStatus.parseSupplierStatus("AVAILABLE"))
                .setPaymentMethodId("CASH")
                .setSupplierCode("1234")
                .setEmployeeId(1)
        );
        suppliers.add(new Supplier()
                .setId(2)
                .setFullName("Anh Duy Khánh")
                .setPhone("0787876765")
                .setSupplierCode("SPL0001")

        );
        suppliers.add(new Supplier()
                .setId(3)
                .setFullName("Chị Bình Nguyên")
                .setEmployeeId(2)
                .setPaymentMethodId("2")
        );
    }

    @BeforeEach
    public void init() {
        when(supplierRepository.findAll()).thenReturn(suppliers);
        when(supplierRepository.findById(1)).thenReturn( Optional.of( suppliers.get(0)));
    }


    @Test
    public void testFindAll() {
        Assertions.assertThat(supplierService.findAll()).hasSize(3);
    }

    @Test
    public void testFindById () {
        supplierAssert(supplierService.findById(1), suppliers.get(0));
        try {
            supplierService.findById(5);
        }catch (Exception e){
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "Not found supplier with id: 5");
        }
    }

    @Test
    public void testDeleteById() {
        Integer idSupplier = 1;
        willDoNothing().given(supplierRepository).deleteById(idSupplier);
        supplierService.deleteById(idSupplier);
        Mockito.verify(supplierRepository, times(1)).deleteById(idSupplier);
    }

    @Test
    public void testCreate () {
        CreateSupplierParam createSupplierParam = new CreateSupplierParam()
                .setFullName("Chị khánh vân")
                .setEmail("thanhngan1002@gmail.com")
                .setPhone("0989876565")
                .setDescription("Quen thuoc");
        when(supplierRepository.save(any(Supplier.class))).thenReturn(suppliers.get(2));
        SupplierResult actual = supplierService.create(createSupplierParam);
        supplierAssert(actual, suppliers.get(2));
    }

    public void testUpdate() {
        UpdateSupplierParam updateSupplierParam = new UpdateSupplierParam()
                .setId(3);
                when(supplierRepository.findById(3)).thenReturn(Optional.of(suppliers.get(2)));
                supplierService.update(updateSupplierParam);
    }


    public void supplierAssert(SupplierResult actual, Supplier expected) {
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getSupplierCode()).isEqualTo(expected.getSupplierCode());
        Assertions.assertThat(actual.getStatus()).isEqualTo(expected.getStatus());
        Assertions.assertThat(actual.getCreatedAt()).isEqualTo(expected.getCreatedAt());
        Assertions.assertThat(actual.getUpdatedAt()).isEqualTo(expected.getUpdatedAt());
    }

}
