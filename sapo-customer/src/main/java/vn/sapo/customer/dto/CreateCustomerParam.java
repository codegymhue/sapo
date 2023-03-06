package vn.sapo.customer.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
//import vn.sapo.customer.dto.CustomerGender;
//import vn.sapo.entities.customer.CustomerStatus;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Accessors(chain = true)
public class CreateCustomerParam {
    private Integer id;

    private String customerCode;
    @NotEmpty(message = "Tên khách hàng không đuợc để trống")
    private String fullName;
    @NotEmpty(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    private String description;

    private Integer groupId;
    @NotEmpty(message = "Email không được để trống")
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

    @Override
    public String toString() {
        return "CreateCustomerParam{" +
                "id=" + id +
                ", customerCode='" + customerCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", groupId=" + groupId +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", fax='" + fax + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", group=" + group +
                ", employeeId=" + employeeId +
                ", createAddressParam=" + createAddressParam +
                ", debtTotal=" + debtTotal +
                ", spendTotal=" + spendTotal +
                ", status=" + status +
                '}';
    }
}
