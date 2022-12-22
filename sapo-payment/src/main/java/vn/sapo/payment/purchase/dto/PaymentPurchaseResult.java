package vn.sapo.payment.method.purchase.dto;

import lombok.*;
import lombok.experimental.Accessors;
import vn.sapo.payment.shared.PMTEmployeeResult;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PaymentPurchaseResult {

    private Integer id;

    private BigDecimal paid;

    private Integer employeeId;

    private PMTEmployeeResult employee;

    private Integer purchaseOrderId;

    private Integer paymentMethodId;

    private Instant createAt;

}
