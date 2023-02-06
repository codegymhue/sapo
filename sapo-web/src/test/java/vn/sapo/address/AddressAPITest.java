package vn.sapo.address;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;
import vn.sapo.controllers.address.AddressAPI;
import vn.sapo.shared.parsers.JacksonParser;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressAPI.class)
public class AddressAPITest {
    @MockBean
    AddressService addressService;
    @Autowired
    private MockMvc mockMvc;
    private static final List<AddressResult> addresses = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        addresses.add(
                new AddressResult()
                        .setId(1)
                        .setFullName("Tran Van Cu")
                        .setPhoneNumber("0987654321")
                        .setLine1("28 Nguyen Tri Phuong")
                        .setLine2("5 Floor")
                        .setCustomerId(1)
        );
    }

    @BeforeEach
    public void init() {
        when(addressService.findByCustomerId(1)).thenReturn(addresses);
        when(addressService.create(isA(CreateAddressParam.class))).thenReturn(addresses.get(0));
        when(addressService.update(isA(UpdateAddressParam.class))).thenReturn(addresses.get(0));

    }

    @Test
    public void testFindByCustomerId() throws Exception {
        mockMvc.perform(
                        get("/api/addresses/findByCustomerId/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    public void testCreateAddress() throws Exception {
        CreateAddressParam createAddressParam = new CreateAddressParam()
                .setFullName("Tran Van Cu")
                .setPhoneNumber("0987654321")
                .setLine1("28 Nguyen Tri Phuong")
                .setLine2("5 Floor")
                .setCustomerId(1);
        mockMvc.perform(
                        post("/api/addresses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonParser.INSTANCE.toJson(createAddressParam))
                                .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

    }
    @Test
    public void testUpdateAddress() throws Exception {
        UpdateAddressParam updateAddressParam = new UpdateAddressParam()
                .setFullName("Tran Van Cu")
                .setPhoneNumber("0987654321")
                .setLine1("28 Nguyen Tri Phuong")
                .setLine2("5 Floor")
                .setCustomerId(1);
        mockMvc.perform(
                        put("/api/addresses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonParser.INSTANCE.toJson(updateAddressParam))
                                .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

    }

}
