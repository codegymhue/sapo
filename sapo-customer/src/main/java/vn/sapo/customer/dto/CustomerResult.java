package vn.sapo.customer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.entities.customer.CustomerStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CustomerResult {

    private Integer id;

    private String customerCode;

    private String name;

    private String phone;

    private CustomerGroup customerGroup;

    private String email;

    private String birthday;

    private BigDecimal debtTotal;

    private BigDecimal spendTotal;

    private CustomerStatus customerStatus;

    private Instant createdAt;

    private Instant updatedAt;

    private Integer employeeId;

    private CusEmployeeResult employee;

    private AddressResult shippingAddress;

    private AddressResult billAddress;

    private List<AddressResult> addressList;

    private CustomerGender customerGender;


    private Integer quantityProductOrder;

    private Integer quantityItemOrder;

    private Instant lastDayOrder;

}

