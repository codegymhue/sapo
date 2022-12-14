package vn.sapo.customer.dto;

import com.phpn.employee.dto.EmployeeResult;
import lombok.*;
import lombok.experimental.Accessors;
import vn.fx.qh.sapo.entities.customer.ShippingAddress;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CustomerOrderResult {

    private Integer id;

    private String customerCode;

    private String name;

    private String phone;

    private Set<ShippingAddress> shippingAddressSet;

    private Integer employeeId;

    private EmployeeResult employee;

    private Boolean deleted;


}

