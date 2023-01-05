package vn.sapo.payment.method;


import vn.sapo.payment.method.dto.CreatePaymentMethodParam;
import vn.sapo.payment.method.dto.PaymentMethodResult;
import vn.sapo.payment.method.dto.UpdatePaymentMethodParam;

import java.util.List;

public interface PaymentMethodService {

    List<PaymentMethodResult> findAll();

    PaymentMethodResult findById(Integer id);

    PaymentMethodResult create(CreatePaymentMethodParam id);

    PaymentMethodResult update(UpdatePaymentMethodParam id);

}