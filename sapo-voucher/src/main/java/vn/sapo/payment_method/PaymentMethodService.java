package vn.sapo.payment_method;


import vn.sapo.payment_method.dto.CreatePaymentMethodParam;
import vn.sapo.payment_method.dto.PaymentMethodResult;
import vn.sapo.payment_method.dto.UpdatePaymentMethodParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentMethodService {

    List<PaymentMethodResult> findAll();

    PaymentMethodResult findById(Integer id);

    PaymentMethodResult findByTitle(String title);

    PaymentMethodResult create(CreatePaymentMethodParam id);

    PaymentMethodResult update(UpdatePaymentMethodParam id);

}