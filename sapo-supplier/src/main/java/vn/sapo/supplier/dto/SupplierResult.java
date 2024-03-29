package vn.sapo.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.customers.dto.AddressResult;
import vn.sapo.entities.supplier.SupplierStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SupplierResult {
    private Integer id;

    private String supplierCode;

    private String fullName;

    private String email;

    private String phone;

    private String description;

    private SupplierStatus status;

    private SupEmployeeResult employee;

    private SupPaymentMethodResult paymentMethod;

    private SupGroupResult group;

    private Instant createdAt;

    private Instant updatedAt;

    private BigDecimal debtTotal;

    private List<AddressResult> addresses;
    private List<ContactResult> contacts;

    private String taxCode;

    private String fax;

    private String website;

    private List<String> tags;

}
