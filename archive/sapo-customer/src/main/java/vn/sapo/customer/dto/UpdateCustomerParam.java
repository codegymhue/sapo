package vn.sapo.customer.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerGroup;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class UpdateCustomerParam {

    private Integer id;

    private String code;

    private String fullName;

    private String phoneNumber;

    private CustomerGroup group;

    private String email;

    private Instant birthday;

    private CustomerGender gender;
}
