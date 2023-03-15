package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseSupplierParam {

    @NotBlank(message = "{supplier.validation.fullName.notBlank}")
    private String fullName;

    @NullOrNotBlank
    private String email;

    @NullOrNotBlank
    private String phone;
    private Integer groupId;

    @NullOrNotBlank
    private String description;
    private Integer employeeId;

    @NullOrNotBlank
    private String paymentMethodId;
    private Integer defaultPricingPolicyId;

    @NullOrNotBlank
    private String taxCode;

    @NullOrNotBlank
    private String fax;

    @NullOrNotBlank
    private String website;
    private List<String> tags;
}
