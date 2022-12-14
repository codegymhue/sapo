package vn.sapo.customer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CustomerParam {

    private Integer id;

    private String customerCode;

    private String name;

    private String phone;


    private Instant createAt;


    private Integer employeeId;


}
