package vn.sapo.address;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {
    @MockBean
    AddressRepository addressRepository;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    AddressService addressService;

    @Autowired
    UpdateAddressParam updateAddressParam;

    @TestConfiguration
    public static class AddressServiceTestConfiguration {
        @Bean
        public ModelMapper getModelMapper() {
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
            return mapper;
        }

        @Bean
        AddressService addressService() {
            return new AddressServiceImpl();
        }

        @Bean
        AddressMapper addressMapper() {
            return new AddressMapper();
        }

        @Bean
        UpdateAddressParam updateAddressParam() {
            return new UpdateAddressParam();
        }
    }

    private static final List<Address> addresses = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        addresses.add(new Address().setId(1).setFullName("Tran Van Cu").setPhoneNumber("0987654321").setLine1("28 Nguyen Tri Phuong").setCustomerId(1));
        addresses.add(new Address().setId(2).setFullName("Thanh Van Cu").setPhoneNumber("0989897688").setLine1("30 Nguyen Tri Phuong").setLine2("3 Floor").setCustomerId(2));
        addresses.add(new Address().setId(3).setFullName("Chị Thanh Hoan").setLine1("30 Nguyen Tri Phuong").setCustomerId(2));
        addresses.add(new Address().setId(4).setFullName("Chị Ánh ngọc").setLine1("35 Nguyen Tri Phuong").setCustomerId(4));
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
//        when(addressRepository.existsById(1)).thenReturn(false);
//        when(addressRepository.save(any()))
//                .thenReturn(addresses.get(1));
        when(addressRepository.existsById(4)).thenReturn(true);
        when(addressRepository.save(any()))
                .thenReturn(addresses.get(3));
    }

    @Test
    public void testFindByCustomerId() {
        List<AddressResult> dtoList = addressService.findByCustomerId(2);
        assertAddress(dtoList.get(0), addresses.get(1));
        assertAddress(dtoList.get(1), addresses.get(2));

//        try {
//            assertAddress(dtoList.get(2), addresses.get(3));
//        } catch (Exception e) {
//            assertThat(e, instanceOf(IndexOutOfBoundsException.class));
//            assertEquals(e.getMessage(), "Customer not found");
//        }
    }

    @Test
    public void testFindAll() {
        Assertions.assertThat(addressService.findAll()).hasSize(2);
    }

    @Test
    public void testFindAll2() {
        try {
            addressService.findAll();
        } catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "find all not possible");
        }
    }

    @Test
    public void testFindById() {
        assertAddress(addressService.findById(2), addresses.get(1));
        assertAddress(addressService.findById(2), addresses.get(1));
        try {
            addressService.findById(3);
        } catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "address not found");
        }
    }

    @Test
    public void testUpdate() {
        UpdateAddressParam updateAddressParam = new UpdateAddressParam()
                .setId(4)
                .setFullName("Chị Ánh ngọc")
                .setLine1("45 Nguyen hue");
//        when(addressRepository.existsById(4)).thenReturn(true);
       AddressResult actual =  addressService.update(updateAddressParam);
       assertAddress(actual, addresses.get(3));
    }

    @Test
    public void testCreate() {
        CreateAddressParam createAddressParam = new CreateAddressParam()
                .setFullName("Chị Ánh ngọc")
                .setLine1("35 Nguyen Tri Phuong")
                .setCustomerId(4);
        AddressResult actual = addressService.create(createAddressParam);
        assertAddress(actual, addresses.get(3));
    }

    public void assertAddress(AddressResult actual, Address expected) {
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
    }

//    public void assertAddress(AddressResult actual) {
//        addresses.forEach(expected -> assertAddress(actual, expected));
//    }
}
