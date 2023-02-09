package vn.sapo.supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vn.sapo.controllers.supplier.SupplierAPI;
import vn.sapo.supplier.dto.SupplierResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupplierAPI.class)
public class SupplierAPITest {
    @MockBean
    SupplierService supplierService;

    @Autowired
    private MockMvc mockMvc;

    private static final List<SupplierResult> suppliers = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        suppliers.add(new SupplierResult()
                        .setId(1)
                        .setName("Thanh Ngân")
                        .setEmail("thanhngan1002@gmail.com")
                        .setPhone("0989876565")
                        .setDescription("Quen thuoc")
                        .setSupplierCode("SPL0001")
//                        .setPaymentMethodId()
                        .setSupplierCode("1234")
                        .setEmployeeId(1)
                );
    }
    @BeforeEach
    public void init() {
        when(supplierService.findById(1)).thenReturn(suppliers.get(0));
    }
    @Test
    public void testFindById () throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/admin/suppliers/1/histories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)));
    }
}
