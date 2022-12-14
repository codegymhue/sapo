package vn.sapo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.*;

import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
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
