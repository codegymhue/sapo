package vn.sapo.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CreateCustomerParam {
    private Integer id;

    @NotEmpty(message = "{customer.validation.title.notBlank}")
    private String fullName;

    @NullOrNotBlank
    @Length(max = 50, message = "{customer.validation.customerCode.length}")
    private String customerCode;

    private CustomerGroupResult group;

    @NullOrNotBlank
    private String phoneNumber;

    @NullOrNotBlank
    private String email;

    private CreateAddressParam createAddressParam;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    @Length(max = 50, message = "{customer.validation.fax.length}")
    private String fax;

    @Length(max = 50, message = "{customer.validation.taxCode.length}")
    private String taxCode;

    private String website;

    private BigDecimal debtTotal;

    private BigDecimal spendTotal;

    private Integer employeeId;

    private String description;

    private List<String> tags;

    private Integer groupId;

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
