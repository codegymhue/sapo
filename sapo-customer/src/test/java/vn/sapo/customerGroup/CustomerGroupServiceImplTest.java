package vn.sapo.customerGroup;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerGroupServiceImplTest {

    @Mock
    private CustomerGroupRepository customerGroupRepository;

    @InjectMocks
    private CustomerGroupServiceImpl customerGroupService;

    private CustomerGroup customerGroup;

    @BeforeEach
    void setUp() {
        customerGroup = new CustomerGroup(1)
                .setCusGrpCode("codeTest");
    }

    @Test
    void validationByTitleTest() {
        String title = "test";
        when(customerGroupRepository.existsByTitle(title)).thenReturn(true);

        Assertions.assertThrows(ValidationException.class, () -> customerGroupService.validationByTitle(title));
    }

    @Test
    void findCustomerGroupByIdTest() {

        when(customerGroupRepository.findById(1)).thenReturn(Optional.of(customerGroup));

        CustomerGroup test = customerGroupService.findCustomerGroupById(1);

        Assertions.assertEquals(test.getId(), customerGroup.getId());
        Assertions.assertEquals(test.getCusGrpCode(), customerGroup.getCusGrpCode());
        Assertions.assertEquals(customerGroup, test);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenNotFound() {

        Assertions.assertThrows(NotFoundException.class, () -> customerGroupService.findCustomerGroupById(2));
    }

    @Test
    void findAllCustomerGroupTest() {
        CustomerGroup c1 = new CustomerGroup(2)
                .setCusGrpCode("codeTest2");

        when(customerGroupRepository.findAll()).thenReturn(Arrays.asList(customerGroup, c1));

        List<CustomerGroup> test = customerGroupService.findAllCustomerGroup();

        Assertions.assertNotNull(test);
        Assertions.assertEquals(2, test.size());
    }
}