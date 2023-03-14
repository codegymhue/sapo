package vn.sapo.customer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.customers.dto.AddressResult;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerResult {
    private String customerCode;
    private Integer id;
    private CusGroupResult group;
    private String fullName;

    private String phoneNumber;

    private String email;

    private Instant birthday;


    private CustomerGender gender;

    private String description;

    private BigDecimal debtTotal;

    private BigDecimal spendTotal;

    private CustomerStatus status;


    private CusEmployeeResult employee;

    private AddressResult shippingAddress;

    private AddressResult billAddress;

    private List<AddressResult> addresses;

    private int quantityProductOrder;

    private int quantityItemOrder;

    private Instant lastDayOrder;

    private PaymentMethod payment;

    private  String taxCode;

    private  String fax;

    private String website;

    private Instant createdAt;

    private Instant updatedAt;

}

