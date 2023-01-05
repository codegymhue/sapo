package vn.sapo.customer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.AddressResult;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerOrderResult {

    private Integer id;

    private String code;

    private String name;

    private String phone;

    private List<AddressResult> addressList;

    private Integer employeeId;

    private CusEmployeeResult employee;

    private Boolean deleted;


}

