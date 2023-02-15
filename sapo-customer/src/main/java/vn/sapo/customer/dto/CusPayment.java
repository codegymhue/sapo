package vn.sapo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.sapo.entities.payment.PaymentMethod;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CusPayment {
    private PaymentMethod title;
}
