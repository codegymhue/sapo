package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateSupplierParam {
    private Integer id;
    private String supplierCode;
    private String fullName;
    private String email;
    private String phone;
    private String description;
    private String paymentMethodId;
    private Integer groupId;
    private Integer employeeId;
    private SupplierStatus status;

}
