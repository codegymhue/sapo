package vn.sapo.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class PaymentMethod {
    private String id;
    private String title;
}
