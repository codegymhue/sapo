package vn.sapo.address;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;
import vn.sapo.entities.Address;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(AddressConfigurationTest.class)
public class AddressServiceTest {

    @MockBean
    AddressRepository addressRepository;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    AddressService addressService;

    private static final List<Address> addresses = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        addresses.add(new Address()
                .setId(1)
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
                .setShippingAddress(true)
        );
        addresses.add(new Address()
                .setId(2)
                .setFullName("Tran Van Cu")
                .setEmail("cutran23@gmail.com")
                .setPhoneNumber("0987654321")
                .setLine1("28 Nguyen Tri Phuong")
                .setCustomerId(1));

        addresses.add(new Address()
                .setId(3)
                .setFullName("Thanh thanh")
                .setEmail("thanh@gmail.com")
                .setPhoneNumber("0989897688")
                .setLine1("30 Nguyen Tri Phuong")
                .setLine2("3 Floor")
                .setShippingAddress(true)
                .setReceiveBill(true)
                .setCustomerId(2));

        addresses.add(new Address()
                .setId(4)
                .setFullName("Chị Thanh Hoan")
                .setCustomerId(2));

        addresses.add(new Address()
                .setId(5)
                .setFullName("Chị Ánh ngọc")
                .setLine1("35 Nguyen Tri Phuong")
                .setCustomerId(4));
    }

    @BeforeEach
    public void init() {
        List<Address> newList = addresses.stream().filter(address -> address.getCustomerId() == 2).collect(Collectors.toList());
//        List a = new ArrayList();
//        for (Address address : addresses) {
//            if(address.getCustomerId()==2)
//                a.add(address);
//        }
        when(addressRepository.findAllByCustomerId(anyInt())).thenReturn(newList);
        when(addressRepository.findAll()).thenReturn(addresses);
        when(addressRepository.findById(1)).thenReturn(Optional.of(addresses.get(1)));
        when(addressRepository.existsById(1)).thenReturn(false);
//        when(addressRepository.save(any()))
//                .thenReturn(addresses.get(3));
    }

    @Test
    public void testFindByCustomerId() {
        List<AddressResult> dtoList = addressService.findByCustomerId(2);
        assertAddress(dtoList.get(0), addresses.get(2));
        assertAddress(dtoList.get(1), addresses.get(3));
        try {
            addressService.findByCustomerId(7);
        } catch (Exception e) {
            assertThat(e, instanceOf(IndexOutOfBoundsException.class));
            assertEquals(e.getMessage(), "Customer not found");
        }
    }


    @Test
    public void testFindAll() {
        Assertions.assertThat(addressService.findAll()).hasSize(5);
        try {
            addressService.findAll().isEmpty();
        } catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "find all not possible");
        }
    }

    @Test
    public void testFindById() {
        assertAddress(addressService.findById(1), addresses.get(1));
    }

    @Test
    public void testNotFindById() {
        try {
            addressService.findById(3);
        } catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "address not found");
        }
    }

    @Test
    public void givenCustomerId_whenDeleteCustomer_thenNothing() {
        int cusId = 1;
        willDoNothing().given(addressRepository).deleteByCustomerId(cusId);
        addressService.deleteByCustomerId(cusId);
        Mockito.verify(addressRepository, times(1)).deleteByCustomerId(cusId);
    }

    @Test
    public void testUpdate() {
        when(addressRepository.existsById(4)).thenReturn(true);
        when(addressRepository.save(any(Address.class))).thenReturn(addresses.get(3));
        UpdateAddressParam updateAddressParam = new UpdateAddressParam()
                .setId(4)
                .setFullName("Chị anh ngoc")
                .setLine1("45 Nguyen hue");
        AddressResult actual = addressService.update(updateAddressParam);
        assertAddress(actual, addresses.get(3));
    }

    @Test
    public void testCreate() {
        CreateAddressParam createAddressParam = new CreateAddressParam()
                .setFullName("Chị Ánh ngọc")
                .setLine1("35 Nguyen Tri Phuong")
                .setCustomerId(6);
        when(addressRepository.save(any(Address.class))).thenReturn(addresses.get(4));
        AddressResult actual = addressService.create(createAddressParam);
        assertAddress(actual, addresses.get(4));
    }

    public void assertAddress(AddressResult actual, Address expected) {
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
        Assertions.assertThat(actual.isShipping()).isEqualTo(expected.isShippingAddress());
        Assertions.assertThat(actual.getZipCode()).isEqualTo(expected.getZipCode());
    }


}
