package vn.sapo.customerGroup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerGroupServiceImplTest {
    @Mock
    private CustomerGroupRepository customerGroupRepository;

    @Test
    public void testGetMaxSystemCustomerGroupCode() {
        String prefix = "CTN";
        String maxCode = prefix + "00005"; // Set up the highest existing code in the system
        when(customerGroupRepository.getMaxSystemCustomerGroupCode()).thenReturn(maxCode);

        CustomerGroupServiceImpl generator = new CustomerGroupServiceImpl(customerGroupRepository);
        String newCode = generator.getMaxSystemCustomerGroupCode();

        assertEquals("CG00006", newCode); // Verify that the new code is generated correctly
    }

}