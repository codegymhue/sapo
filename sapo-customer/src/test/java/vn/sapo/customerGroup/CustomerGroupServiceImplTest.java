package vn.sapo.customerGroup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerGroupServiceImplTest {

    @Mock
    private CustomerGroupRepository customerGroupRepository;

    @Autowired
    private CustomerGroupServiceImpl customerGroupService;


    @Test
    public void testGetMaxSystemCustomerGroupCode() {
        String prefix = "CTN";
        String maxCode = prefix + "00005";
        when(customerGroupRepository.getMaxSystemCustomerGroupCode()).thenReturn(maxCode);

        CustomerGroupServiceImpl generator = new CustomerGroupServiceImpl(customerGroupRepository);
        String newCode = generator.getMaxSystemCustomerGroupCode();

        assertEquals("CTN00006", newCode);
    }

}