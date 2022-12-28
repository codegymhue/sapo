package vn.sapo.payment.sale.dto;

import lombok.*;
import lombok.experimental.Accessors;
import vn.sapo.order.sale.dto.SaleOrderResult;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PaymentSaleOrderResult {

    private Integer id;

    private BigDecimal paid;

    private Integer employeeId;

    private Integer orderId;

    private Integer paymentMethodId;

    private Instant createdAt;

    private String description;
}
