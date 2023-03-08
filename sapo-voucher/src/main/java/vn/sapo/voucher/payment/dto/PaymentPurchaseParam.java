package vn.sapo.voucher.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentPurchaseParam {

    private Integer id;

    private BigDecimal paid;


    private Integer purchaseOrderId;

    private Integer paymentMethodId;

    private Instant createdAt;

}
