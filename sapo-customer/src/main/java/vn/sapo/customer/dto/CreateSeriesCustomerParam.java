package vn.sapo.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import vn.sapo.customers.dto.CreateAddressParam;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Component
public class CreateSeriesCustomerParam {
    private HashMap<Integer, List<CreateAddressParam>> listObjAddress;
    private List<CreateCustomerParam> listResultParam;
    private String errorMessage;
    @NotBlank
    @Email
    private String email;
    private String emailFrom;
    private String passAppEmailFrom;

    @Override
    public String toString() {
        return "CreateSeriesCustomerParam{" +
                "listObjAddress=" + listObjAddress +
                ", listResultParam=" + listResultParam +
                ", errorMessage='" + errorMessage + '\'' +
                ", email='" + email + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                ", passAppEmailFrom='" + passAppEmailFrom + '\'' +
                '}';
    }
}
