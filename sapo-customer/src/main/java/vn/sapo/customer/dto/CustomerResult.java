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

    private String code;

    private String fullName;

    private String phoneNumber;

    private String email;

    private Instant birthday;

    private CustomerGroup group;

    private CustomerGender gender;

    private BigDecimal debtTotal;

    private BigDecimal spendTotal;

    private CustomerStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    private CusEmployeeResult employee;

    private AddressResult shippingAddress;

    private AddressResult billAddress;

    private List<AddressResult> addresses;

    private int quantityProductOrder;

    private int quantityItemOrder;

    private Instant lastDayOrder;
}

