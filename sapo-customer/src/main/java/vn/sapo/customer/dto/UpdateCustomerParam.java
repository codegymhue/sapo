package vn.sapo.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateCustomerParam {
    @NotNull
    private Integer id;

    @NullOrNotBlank
    private String customerCode;

    @NullOrNotBlank
    private String fullName;

    @NullOrNotBlank
    private String phoneNumber;

    private Integer groupId;
    @NullOrNotBlank
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    private CustomerStatus status;

    @NullOrNotBlank
    private String description;
    @NullOrNotBlank
    private String website;
    @NullOrNotBlank
    private String fax;
    @NullOrNotBlank
    private String taxCode;


}
