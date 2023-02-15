package vn.sapo.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateCustomerParam {

    private Integer id;

    private String customerCode;

    private String fullName;

    private String phoneNumber;

    private Integer groupId;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    private Integer employeeId;

    private CustomerStatus status;

    private String description;
    private String website;
    private String fax;
    private String taxCode;



}
