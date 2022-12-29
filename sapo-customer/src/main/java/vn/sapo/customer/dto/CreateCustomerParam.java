package vn.sapo.customer.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.entities.customer.CustomerGender;

import java.time.Instant;


@Getter
@Setter
@Accessors(chain = true)
public class CreateCustomerParam {
    
    private Integer id;

    private String customerCode;

    private String fullName;

    private String phone;

    private String groupId;

    private String email;

    private Instant birthday;

    private CustomerGender gender;

    private Integer employeeId;

    private CreateAddressParam createAddressParam;

}
