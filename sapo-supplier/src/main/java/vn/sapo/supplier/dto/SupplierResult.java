package vn.sapo.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

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


    private SupplierGroupResult supplierGroupResult;
    private Integer supGroupId;
    private String supGroupTitle;
    private Instant createAt;
    private Instant updateAt;
    private BigDecimal debtTotal;
    private List<AddressResult> addresses;
    private  String taxCode;
    private  String fax;
    private String website;
}
