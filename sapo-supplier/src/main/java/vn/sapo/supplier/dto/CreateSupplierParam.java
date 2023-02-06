package vn.sapo.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.entities.supplier.SupplierStatus;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
public class CreateSupplierParam {
    private Integer id;
    private String supplierCode;
    private String name;
    private String email;
    private String phone;
    private String description;
    private Integer employeeId;
    private Integer paymentMethodId;
    private Instant createdAt;
    private Instant updatedAt;
    private CreateAddressParam createAddressParam;
    private SupplierStatus status;
    private String attFax;
    private String attTaxCode;
    private String attWebsite;
    private BigDecimal debtTotal;


}