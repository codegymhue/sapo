package vn.sapo.payment.purchase;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class CreatePaymentPurchaseParam {

    private Integer id;

    private BigDecimal paid;

    private Integer employeeId;

    private Integer purchaseOrderId;

    private Integer paymentMethodId;

}
