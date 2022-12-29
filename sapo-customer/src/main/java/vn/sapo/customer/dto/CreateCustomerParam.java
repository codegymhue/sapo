package vn.sapo.customer.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.entities.customer.CustomerGender;

import java.time.Instant;
import java.util.Date;


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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    private Integer employeeId;

    private CreateAddressParam createAddressParam;

}
