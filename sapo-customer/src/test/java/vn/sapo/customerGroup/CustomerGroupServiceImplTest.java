package vn.sapo.customerGroup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerGroupServiceImplTest {

    @Mock
    private CustomerGroupRepository customerGroupRepository;

    @Test
    public void testGetMaxSystemCustomerGroupCode() {
        String prefix = "CTN";
        String maxCode = prefix + "00005";
        when(customerGroupRepository.getMaxSystemCustomerGroupCode()).thenReturn(maxCode);

        CustomerGroupServiceImpl generator = new CustomerGroupServiceImpl(customerGroupRepository);
        String newCode = generator.getMaxSystemCustomerGroupCode();

        assertEquals("CTN00006", newCode);
    }

    @Test
    public void testCheckCusGrpCodeWhenNotEmpty() {
        CustomerGroupServiceImpl customerGroup = new CustomerGroupServiceImpl(customerGroupRepository);
        Map<Object, Object> errors = new HashMap<>();

        String cusGrpCode1 = "CTN00001";
        customerGroup.checkCusGrpCodeWhenNotEmpty(cusGrpCode1, errors);
        assertEquals("Mã nhóm không được có tiền tố của hệ thống CTN", errors.get("cusGrpCode"));

        String cusGrpCode2 = "CTN1234567890123456789012345678901234567890123456789012345";
        customerGroup.checkCusGrpCodeWhenNotEmpty(cusGrpCode2, errors);
        assertEquals("Mã nhóm khách hàng không được vượt quá 50 ký tự", errors.get("cusGrpCode"));
    }

}