package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.supplier.SupplierStatus;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateSupplierParam {
    private Integer id;
    private String supplierCode;
    private String name;
    private String email;
    private String phone;
    private String description;
    private Integer paymentMethodId;
    private String supGroupName;
    private Integer employeeId;
    private SupplierStatus status;

}
