package vn.sapo.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateCustomerParam {

    private Integer id;

    @NotBlank(message = "{customer.validation.fullName.notBlank}")
    @Length(max = 255, message = "{customer.validation.fullName.length}")
    private String fullName;

    @NotBlank(message = "{customer.validation.customerCode.notBlank}")
    @Length(max = 50, message = "{customer.validation.customerCode.length}")
    private String customerCode;

    private Integer groupId;

    @NullOrNotBlank
    @Pattern(regexp = "^[0-9]{8,15}$", message = "{customer.validation.phoneNumber.pattern}")
    private String phoneNumber;

    @NullOrNotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{customer.validation.email.pattern}")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    @NullOrNotBlank
    @Pattern(regexp = "^[0-9]{8,15}$", message = "{customer.validation.fax.pattern}")
    private String fax;

    @NullOrNotBlank
    @Length(max = 50, message = "{customer.validation.taxCode.length}")
    private String taxCode;
    private String website;

    private CustomerStatus status;

    private Integer employeeId;

    @NullOrNotBlank
    @Length(max = 500, message = "{customer.validation.description.length}")
    private String description;

    private List<String> tags;

}
