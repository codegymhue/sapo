package vn.sapo.address;

import org.assertj.core.api.Assertions;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.entities.customer.Customer;

public class CustomerAssertTest {
    public static void assertCustomer(CustomerResult expected,  Customer actual){
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getCustomerCode()).isEqualTo(expected.getCustomerCode());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getPhoneNumber()).isEqualTo(expected.getPhoneNumber());
        Assertions.assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        Assertions.assertThat(actual.getBirthday()).isEqualTo(expected.getBirthday());
        Assertions.assertThat(actual.getGroupId()).isEqualTo(expected.getGroup().getId());
//        Assertions.assertThat(actual.getGender()).isEqualTo(expected.getGender());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());

//        Assertions.assertThat(actual.getStatus()).isEqualTo(expected.getStatus());
        Assertions.assertThat(actual.getCreatedAt()).isEqualTo(expected.getCreatedAt());
        Assertions.assertThat(actual.getUpdatedAt()).isEqualTo(expected.getUpdatedAt());
        Assertions.assertThat(actual.getEmployee().getFullName()).isEqualTo(expected.getEmployee().getFullName());
//        Assertions.assertThat(actual.getShippingAddress().getDistrictName()).isEqualTo(expected.getShippingAddress().getDistrictName());
//        Assertions.assertThat(actual.getBillAddress().).isEqualTo(expected.getBillAddress());
//        Assertions.assertThat(actual.getAddresses()).isEqualTo(expected.getAddresses());

        Assertions.assertThat(actual.getTaxCode()).isEqualTo(expected.getTaxCode());
        Assertions.assertThat(actual.getFax()).isEqualTo(expected.getFax());
        Assertions.assertThat(actual.getWebsite()).isEqualTo(expected.getWebsite());
    }
    public static void assertCustomerResult(CustomerResult actual, CustomerResult expected){
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getCustomerCode()).isEqualTo(expected.getCustomerCode());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getPhoneNumber()).isEqualTo(expected.getPhoneNumber());
        Assertions.assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        Assertions.assertThat(actual.getBirthday()).isEqualTo(expected.getBirthday());
        Assertions.assertThat(actual.getGroup()).isEqualTo(expected.getGroup());
        Assertions.assertThat(actual.getGender()).isEqualTo(expected.getGender());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getDebtTotal()).isEqualTo(expected.getDebtTotal());
        Assertions.assertThat(actual.getSpendTotal()).isEqualTo(expected.getSpendTotal());
        Assertions.assertThat(actual.getStatus()).isEqualTo(expected.getStatus());
        Assertions.assertThat(actual.getCreatedAt()).isEqualTo(expected.getCreatedAt());
        Assertions.assertThat(actual.getUpdatedAt()).isEqualTo(expected.getUpdatedAt());
        Assertions.assertThat(actual.getEmployee()).isEqualTo(expected.getEmployee());
        Assertions.assertThat(actual.getShippingAddress()).isEqualTo(expected.getShippingAddress());
        Assertions.assertThat(actual.getBillAddress()).isEqualTo(expected.getBillAddress());
        Assertions.assertThat(actual.getAddresses()).isEqualTo(expected.getAddresses());
        Assertions.assertThat(actual.getQuantityProductOrder()).isEqualTo(expected.getQuantityProductOrder());
        Assertions.assertThat(actual.getQuantityItemOrder()).isEqualTo(expected.getQuantityItemOrder());
        Assertions.assertThat(actual.getLastDayOrder()).isEqualTo(expected.getLastDayOrder());
        Assertions.assertThat(actual.getPayment()).isEqualTo(expected.getPayment());
        Assertions.assertThat(actual.getTaxCode()).isEqualTo(expected.getTaxCode());
        Assertions.assertThat(actual.getStatus()).isEqualTo(expected.getStatus());
        Assertions.assertThat(actual.getCreatedAt()).isEqualTo(expected.getCreatedAt());
        Assertions.assertThat(actual.getUpdatedAt()).isEqualTo(expected.getUpdatedAt());
        Assertions.assertThat(actual.getEmployee()).isEqualTo(expected.getEmployee());
        Assertions.assertThat(actual.getShippingAddress()).isEqualTo(expected.getShippingAddress());
        Assertions.assertThat(actual.getBillAddress()).isEqualTo(expected.getBillAddress());
        Assertions.assertThat(actual.getAddresses()).isEqualTo(expected.getAddresses());
        Assertions.assertThat(actual.getTaxCode()).isEqualTo(expected.getTaxCode());
        Assertions.assertThat(actual.getFax()).isEqualTo(expected.getFax());
        Assertions.assertThat(actual.getWebsite()).isEqualTo(expected.getWebsite());
    }
    public static CreateAddressParam getCreateAddressParam(){
        return new CreateAddressParam()
                .setCustomerId(1)
                .setFullName("trung")
                .setPhoneNumber("089898989989")
                .setEmail("trung@gmail.com")
                .setSupplierId(1)
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
