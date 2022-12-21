package vn.sapo.payment.sale;


import org.springframework.transaction.annotation.Transactional;
import vn.sapo.payment.sale.dto.CreatePaymentSaleOrderParam;
import vn.sapo.payment.sale.dto.PaymentSaleOrderResult;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentSaleOrderService {

    List<PaymentSaleOrderResult> findAll();

    List<PaymentSaleOrderResult> findAllByOrderId(Integer orderId);

    PaymentSaleOrderResult create(CreatePaymentSaleOrderParam createPaymentSaleOrderParam);

    BigDecimal getPaidTotalByCustomerId(Integer id);

    BigDecimal getDebtTotalByCustomerId(Integer customerId);

}
