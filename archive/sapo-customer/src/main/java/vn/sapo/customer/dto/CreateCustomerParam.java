package vn.sapo.customer.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerGroup;

import java.time.Instant;


@Getter
@Setter
@Accessors(chain = true)
public class CreateCustomerParam {
    
    private Integer id;

    private String code;

    private String fullName;

    private String phone;

    private CustomerGroup group;

    private String email;

    private Instant birthday;

    private CustomerGender gender;

    private Integer employeeId;

    private CreateAddressParam createAddressParam;

}
