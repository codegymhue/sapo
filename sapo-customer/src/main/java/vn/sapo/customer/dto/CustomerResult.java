package vn.sapo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.customers.dto.AddressResult;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerResult {
    private Integer id;

    private String fullName;

    private String customerCode;

    private CusGroupResult group;

    private String phoneNumber;

    private String email;

    private Instant birthday;

    private CustomerGender gender;

    private String fax;

    private String taxCode;

    private String website;

    private BigDecimal debtTotal;

    private BigDecimal spendTotal;

    private CusEmployeeResult employee;

    private String description;

    private List<String> tags;

    private CustomerStatus status;

    private AddressResult shippingAddress;

    private AddressResult billAddress;

    private List<AddressResult> addresses;

    private int quantityProductOrder;

    private int quantityItemOrder;

    private Instant lastDayOrder;

    private PaymentMethod payment;

    private Instant createdAt;

    private Instant updatedAt;

    private HashMap<String, String> attributes;
}

