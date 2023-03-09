package vn.sapo.customer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.AddressResult;

import java.util.List;


@NoArgsConstructor
@Data
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

