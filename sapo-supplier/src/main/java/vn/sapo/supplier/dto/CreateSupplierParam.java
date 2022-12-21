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
@Accessors(chain = true)
public class CreateSupplierParam {
    private String supplierCode;
    private String name;
    private String email;
    private String phone;
    private String description;
    private Integer paymentMethodId;
    private Instant createdAt;
    private Instant updatedAt;

}