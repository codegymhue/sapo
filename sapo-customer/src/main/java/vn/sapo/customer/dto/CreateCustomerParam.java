package vn.sapo.customer.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;

import java.math.BigDecimal;
import java.util.Date;


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
    private String website;
    private String fax;
    private String taxCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    private CustomerGroupResult group;

    private Integer employeeId;

    private CreateAddressParam createAddressParam;

    private BigDecimal debtTotal;

    private BigDecimal spendTotal;
    private CustomerStatus status;
}
