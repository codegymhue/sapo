package vn.sapo.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.supplier.SupplierStatus;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SupplierResult {


    private Integer id;
    private String supplierCode;
    private String name;
    private String email;
    private String phone;
    private SupplierStatus status;
    private String description;
    private Integer employeeId;
    private SupEmployeeResult employee;
    private String paymentMethodId;
    private SupPaymentMethodResult paymentMethod;
    private SupGroupResult supGroupResult;
    private String supGroupTitle;

    private Instant createAt;
    private Instant updateAt;

}