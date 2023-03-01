package vn.sapo.customer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.AddressResult;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
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

    private PaymentMethod payment;
    private  String taxCode;
    private  String fax;
    private String website;

    @Override
    public String toString() {
        return "CustomerResult{" +
                "id=" + id +
                ", customerCode='" + customerCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", group=" + group +
                ", gender=" + gender +
                ", description='" + description + '\'' +
                ", debtTotal=" + debtTotal +
                ", spendTotal=" + spendTotal +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", employee=" + employee +
                ", shippingAddress=" + shippingAddress +
                ", billAddress=" + billAddress +
                ", addresses=" + addresses +
                ", quantityProductOrder=" + quantityProductOrder +
                ", quantityItemOrder=" + quantityItemOrder +
                ", lastDayOrder=" + lastDayOrder +
                ", payment=" + payment +
                ", taxCode='" + taxCode + '\'' +
                ", fax='" + fax + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}

