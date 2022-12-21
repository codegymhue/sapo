package vn.sapo.payment.purchase.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.payment.shared.PMTEmployeeResult;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentPurchaseParam {

    private Integer id;

    private BigDecimal paid;

    private PMTEmployeeResult employee;

    private Integer purchaseOrderId;

    private Integer paymentMethodId;

    private Instant createdAt;

}
