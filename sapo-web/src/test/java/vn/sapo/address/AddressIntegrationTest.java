package vn.sapo.address;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.controllers.address.AddressAPI;

@SpringBootTest
public class AddressIntegrationTest {
    @Autowired
    AddressAPI addressAPI;

    private AddressResult addressExpected;

    @BeforeEach
    public void setup() {
        CreateAddressParam createAddressParam = new CreateAddressParam()
                .setFullName("Anh Ngoc")
                .setLine1("28 Phan Chu Trinh, Thanh pho Hue")
                .setLine2("Son Chau, Huong Son Ha Tinh")
                .setEmail("anhngoc@gmail.com")
                .setWardId(23)
                .setWardName("Phan Chu Trinh")
                .setDistrictId(1)
                .setDistrictName("An Cuu")
                .setProvinceId(75)
                .setProvinceName("Thanh Pho Hue")
                .setCustomerId(385)
                .setPhoneNumber("0899624654")
                .setReceiveBill(true)
                .setShipping(true);
        addressExpected = (AddressResult) addressAPI.create(createAddressParam).getBody();
    }

    @Test
    public void findById() {
        AddressResult dto = (AddressResult) addressAPI.findById(addressExpected.getId()).getBody();
        assertAddress(dto, addressExpected);
    }

    public void assertAddress(AddressResult actual, AddressResult expected) {
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getLine1()).isEqualTo(expected.getLine1());
        Assertions.assertThat(actual.getLine2()).isEqualTo(expected.getLine2());
        Assertions.assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        Assertions.assertThat(actual.getDistrictId()).isEqualTo(expected.getDistrictId());
        Assertions.assertThat(actual.getDistrictName()).isEqualTo(expected.getDistrictName());
        Assertions.assertThat(actual.getProvinceId()).isEqualTo(expected.getProvinceId());
        Assertions.assertThat(actual.getProvinceName()).isEqualTo(expected.getProvinceName());
        Assertions.assertThat(actual.getWardId()).isEqualTo(expected.getWardId());
        Assertions.assertThat(actual.getWardName()).isEqualTo(expected.getWardName());
        Assertions.assertThat(actual.getSupplierId()).isEqualTo(expected.getSupplierId());
        Assertions.assertThat(actual.isReceiveBill()).isEqualTo(expected.isReceiveBill());
        Assertions.assertThat(actual.isShipping()).isEqualTo(expected.isShipping());
        Assertions.assertThat(actual.getZipCode()).isEqualTo(expected.getZipCode());
    }
}
