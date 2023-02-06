package vn.sapo.customer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerResult {

    private Integer id;

    private String customerCode;

    private String fullName;

    private String phoneNumber;

    private String email;

    private Instant birthday;

    private CusGroupResult group;

    private CustomerGender gender;

    private String description;

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

    private CusPayment payment;
    private  String taxCode;
    private  String fax;
    private String website;
}

