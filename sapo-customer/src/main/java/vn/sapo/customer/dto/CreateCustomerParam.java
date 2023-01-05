package vn.sapo.customer.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.CustomerGender;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Accessors(chain = true)
public class CreateCustomerParam {

    private Integer id;
    private String customerCode;

    private String fullName;

    private String phoneNumber;

    private String description;

    private Integer groupId;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    private CustomerGroupResult group;

    private List<AddressResult> addresses;

    private Integer employeeId;

    private CreateAddressParam createAddressParam;
}
