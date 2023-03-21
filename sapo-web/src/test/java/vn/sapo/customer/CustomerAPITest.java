package vn.sapo.customer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import vn.sapo.controllers.customer.CustomerAPI;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.customers.AddressService;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.excel.ExcelService;
import vn.sapo.order.sale.SaleOrderService;
import vn.sapo.order.sale.item.OrderItemService;
import vn.sapo.shared.parsers.JacksonParser;
import vn.sapo.voucher.receipt.ReceiptVoucherService;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isA;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerAPI.class)
public class CustomerAPITest {

    @MockBean
    private ExcelService excelService;
    @MockBean
    private SaleOrderService saleOrderService;
    @MockBean
    private ReceiptVoucherService receiptVoucherService;

    @MockBean
    private OrderItemService orderItemService;
    @MockBean
    private AddressService addressService;
    @MockBean
    private CustomerGroupService customerGroupService;
    @MockBean
    private CustomerService customerService;
//    @Autowired
    private MockMvc mockMvc;
    private static CreateAddressParam createAddressParam;
    private static CreateCustomerParam createCustomerParam;
    private static UpdateCustomerParam updateCustomerParam;
    private static List<AddressResult> addressResultList;
    private static List<CustomerResult> customerResultList;

    @BeforeAll
    static void init(){
        createAddressParam = CustomerParamTest.getCreateAddressParam();
        createCustomerParam = CustomerParamTest.getCreateCustomerParam();
        updateCustomerParam = CustomerParamTest.getUpdateCustomerParam();
        addressResultList = CustomerParamTest.getListAddressResult();
        customerResultList = CustomerParamTest.getListCustomerResult();
    }

    @BeforeEach
    public void setUp(){
        Mockito.when(customerService.findAll()).thenReturn(customerResultList);
        Mockito.when(customerService.findById(1)).thenReturn(customerResultList.get(0));
        Mockito.when(customerService.create(isA(CreateCustomerParam.class))).thenReturn(customerResultList.get(0));
//        Mockito.when(customerService.update(isA(UpdateCustomerParam.class))).thenReturn(customerResultList.get(0));
    }

    @Test
    public void findAllCustomer() throws Exception {
        mockMvc.perform(get("/api/customers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    public void findByCustomerById() throws Exception {
        mockMvc.perform(get("/api/customers/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }
    @Test
    public void createCustomer() throws Exception {
        mockMvc.perform(post("/api/customers/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JacksonParser.INSTANCE.toJson(createCustomerParam))
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("$.id",is(1)));
    }
    @Test
    public void updateCustomer() throws Exception {
        mockMvc.perform(put("/api/customers/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JacksonParser.INSTANCE.toJson(updateCustomerParam))
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.id",is(1)));
    }
    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/customers/delete/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk());
    }
}
