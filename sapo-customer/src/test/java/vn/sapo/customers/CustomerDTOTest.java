package vn.sapo.customers;

import vn.sapo.customers.dto.AddressResult;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.customer.dto.*;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class CustomerDTOTest {
    private static Date date = new Date();

    public static List<Customer> getCustomer() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer()
                .setId(1)
                .setCustomerCode("CustomerCode")
                .setDescription("description")
                .setFullName("trung")
                .setPhoneNumber("09090909")
                .setEmail("trung@gmail.com")
                .setBirthday(date.toInstant())
                .setStatus(CustomerStatus.AVAILABLE)
                .setAttributes(setAttribute("098989898", "tax-88787878", "http://sapo.tk08"))
                .setGroupId(1)
                .setEmployeeId(1));
        return customerList;
    }

    public static List<CustomerResult> getCustomerResult() {
        List<CustomerResult> customerResultList = new ArrayList<>();
//        customerResultList.add(new CustomerResult(1,
//                "CustomerCode",
//                "trung",
//                "0890809989",
//                "trung@gmail.com",
//                Instant.now(),
//                new CusGroupResult()
//                        .setId(1)
//                        .setDiscount(121212)
//                        .setTitle("232323")
//                        .setDescription("")
//                        .setCusGrpCode("23213123123"),
//                vn.sapo.customer.dto.CustomerGender.NAM,
//                "description",
//                new BigDecimal(1000),
//                new BigDecimal(1000),
//                vn.sapo.customer.dto.CustomerStatus.AVAILABLE,
//                Instant.now(),
//                Instant.now()
////                new CusEmployeeResult()
////                        .setId(1)
////                        .setFullName("trung"),
////                getListAddressResult().get(0),
////                getListAddressResult().get(1),
////                getListAddressResult(),
////                12,
////                10,
////                Instant.now(),
////                new PaymentMethod()
////                        .setId("1")
////                        .setTitle("visacard"),
////                "tax-09888",
////                "098989898",
////                "http://sapo.tk08")
//        );


//        customerResultList.add(new CustomerResult(1,
//                "customerCode",
//                "trung",
//                "0890809989",
//                "trung@gmail.com",
//                Instant.now(),
//                new CusGroupResult()
//                        .setId(1)
//                        .setDiscount(121212)
//                        .setTitle("232323")
//                        .setDescription("")
//                        .setCusGrpCode("23213123123"),
//                vn.sapo.customer.dto.CustomerGender.NAM,
//                "description",
//                new BigDecimal(1000),
//                new BigDecimal(1000),
//                vn.sapo.customer.dto.CustomerStatus.AVAILABLE,
//                Instant.now(),
//                Instant.now(),
//                new CusEmployeeResult()
//                        .setId(1)
//                        .setFullName("trung"),
//                getListAddressResult().get(0),
//                getListAddressResult().get(1),
//                getListAddressResult(),
//                12,
//                10,
//                Instant.now(),
//                new PaymentMethod()
//                        .setId("1")
//                        .setTitle("visacard"),
//                "tax-09888",
//                "098989898",
//                "http://sapo.tk08")
        //    );
        //       return customerResultList;
        return null;
    }

    public static List<AddressResult> getListAddressResult() {
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

    public static CreateCustomerParam getCreateCustomerParam() {
        return new CreateCustomerParam()
                .setId(1)
                .setCustomerCode("CustomerCode")
                .setCreateAddressParam(getCreateAddressParam())
                .setFullName("trung")
                .setPhoneNumber("09090909")
                .setDescription("description")
                .setGroupId(1)
                .setEmail("trung@gmail.com")
                .setWebsite("http://sapo.tk08")
                .setFax("098989898")
                .setTaxCode("tax-88787878")
                .setBirthday(date)
                .setGender(vn.sapo.customer.dto.CustomerGender.NAM)
//                .setGroup(new CustomerGroupResult(1, "title", "XMGroupCus", 1, "visa",new Date(), 1L, "description", 2))
                .setEmployeeId(1)
                .setDebtTotal(new BigDecimal(1000))
                .setSpendTotal(new BigDecimal(1000))
                .setStatus(vn.sapo.customer.dto.CustomerStatus.AVAILABLE);
    }

    public static CreateAddressParam getCreateAddressParam() {
        return new CreateAddressParam()
                .setCustomerId(1)
                .setFullName("trung")
                .setPhoneNumber("089898989989")
                .setEmail("trung@gmail.com")
                .setSupplierId(1)
                .setLine1("Line1")
                .setLine2("Line2")
                .setDescription("description")
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

    public static HashMap<String, String> setAttribute(String fax, String taxCode, String website) {
        HashMap<String, String> attribute = new HashMap<>();
        attribute.put("fax", fax);
        attribute.put("taxCode", taxCode);
        attribute.put("website", website);
        return attribute;
    }
}
