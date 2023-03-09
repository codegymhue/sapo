package vn.sapo.customer;

import org.springframework.stereotype.Component;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.customer.dto.*;
import vn.sapo.customerGroup.dto.CustomerGroupResult;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class CustomerParamTest {
    public CustomerParamTest(){};
    public static List<CustomerResult> getListCustomerResult(){
        List<CustomerResult> customerResultList = new ArrayList<>();
        customerResultList.add(new CustomerResult(
                1,
                "customerCode",
                "trung",
                "0890809989",
                "trung@gmail.com",
                Instant.now(),
                new CusGroupResult()
                        .setId(1)
                        .setDiscount(121212)
                        .setTitle("232323")
                        .setDescription("")
                        .setCusGrpCode("23213123123"),
                CustomerGender.NAM,
                "description",
                new BigDecimal(1000),
                new BigDecimal(1000),
                CustomerStatus.AVAILABLE,
                Instant.now(),
                Instant.now(),
                new CusEmployeeResult()
                        .setId(1)
                        .setFullName("trung"),
                getListAddressResult().get(0),
                getListAddressResult().get(1),
                getListAddressResult(),
                12,
                10,
                Instant.now(),
                new PaymentMethod()
                        .setId("1")
                        .setTitle("visacard"),
                "tax-09888",
                "08458484849",
                "sapo.vn"
        ));
        customerResultList.add(new CustomerResult(
                2,
                "CustomerCode",
                "trung",
                "0890809",
                "trug@gmail.com",
                Instant.now(),
                new CusGroupResult()
                        .setId(1)
                        .setDiscount(121212)
                        .setTitle("232323")
                        .setDescription("description")
                        .setCusGrpCode("23213123123"),
                CustomerGender.NAM,
                "fsaasdada",
                new BigDecimal(1000),
                new BigDecimal(1000),
                CustomerStatus.AVAILABLE,
                Instant.now(),
                Instant.now(),
                new CusEmployeeResult()
                        .setId(1)
                        .setFullName("trung"),
                getListAddressResult().get(0),
                getListAddressResult().get(1),
                getListAddressResult(),
                12,
                10,
                Instant.now(),
                new PaymentMethod()
                        .setId("1")
                        .setTitle("visacard"),
                "tax-09888",
                "08458484849",
                "sapo.vn"));
                return customerResultList;
    }
    public static List<AddressResult> getListAddressResult(){
        List<AddressResult> addressResultList = new ArrayList<>();
        addressResultList.add(new AddressResult()
                .setId(1)
                .setPhoneNumber("089898989989")
                .setEmail("trung@gmail.com")
                .setSupplierId(1)
                .setLine1("line1")
                .setLine2("line2")
                .setWardId(1)
                .setWardName("phuong Dong Ba")
                .setDistrictId(2)
                .setDistrictName("Thanh pho Hue")
                .setProvinceId(3)
                .setProvinceName("Tinh Thua Thien Hue")
                .setZipCode("Zipcode")
                .setShipping(true)
                .setReceiveBill(false));
        addressResultList.add(new AddressResult()
                .setId(1)
                .setPhoneNumber("089898989989")
                .setEmail("trung@gmail.com")
                .setSupplierId(1)
                .setWardId(1)
                .setWardName("phuong Dong Ba")
                .setDistrictId(2)
                .setDistrictName("Thanh pho Hue")
                .setProvinceId(3)
                .setProvinceName("Tinh Thua Thien Hue"));
        return addressResultList;
    }
    public static UpdateCustomerParam getUpdateCustomerParam(){
        return new UpdateCustomerParam()
                 .setId(1)
                 .setCustomerCode("")
                 .setFullName("trung")
                 .setPhoneNumber("0809809809")
                 .setDescription("description")
                 .setGroupId(1)
                 .setEmail("trung@gmail.com")
                 .setWebsite("sapo.vn")
                 .setFax("0909090909")
                 .setTaxCode("tax-99999")
                 .setBirthday(new Date())
                 .setGender(CustomerGender.NAM)
                 .setEmployeeId(1)
                 .setStatus(CustomerStatus.AVAILABLE);
    }
    public static CreateCustomerParam getCreateCustomerParam(){
        return new CreateCustomerParam()
//                .setId(1)
//                .setCustomerCode("")
                .setCreateAddressParam(getCreateAddressParam())
                .setFullName("trung")
                .setPhoneNumber("0809809809")
                .setDescription("Description")
                .setGroupId(1)
                .setEmail("trung@gmail.com")
                .setWebsite("sapo.vn")
                .setFax("098989898")
                .setTaxCode("tax-88787878")
                .setBirthday(new Date())
                .setGender(CustomerGender.NAM)
                .setGroup(new CustomerGroupResult(1, "title", "XMGroupCus", 1, "visa",new Date(), 1L, "description", 2))
                .setEmployeeId(1)
                .setDebtTotal(new BigDecimal(1000))
                .setSpendTotal(new BigDecimal(1000))
                .setStatus(CustomerStatus.AVAILABLE);
    }
    public static CreateAddressParam getCreateAddressParam(){
        return new CreateAddressParam()
                .setCustomerId(1)
                .setFullName("trung")
                .setPhoneNumber("089898989989")
                .setEmail("trung@gmail.com")
<<<<<<< HEAD
=======
                .setSupplierId(1)
>>>>>>> 71642ef35d224f631ff2cb70f4fbce277fe589fc
                .setLine1("Line1")
                .setLine2("Line2")
                .setWardId(1)
                .setWardName("phuong Thuan Loc")
                .setDistrictId(2)
                .setDistrictName("Thanh Pho Hue")
                .setProvinceId(3)
                .setProvinceName("Thua Thien Hue")
                .setZipCode("Zipcode")
                .setShipping(true)
                .setReceiveBill(false);
    }

}
