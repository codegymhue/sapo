package vn.sapo.payment.method;


import org.springframework.stereotype.Service;
import vn.sapo.payment.method.dto.CreatePaymentMethodParam;
import vn.sapo.payment.method.dto.PaymentMethodResult;
import vn.sapo.payment.method.dto.UpdatePaymentMethodParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface PaymentMethodService {

    List<PaymentMethodResult> findAll();

    PaymentMethodResult findById(Integer id);

    PaymentMethodResult findByTitle(String title);

    PaymentMethodResult create(CreatePaymentMethodParam id);

    PaymentMethodResult update(UpdatePaymentMethodParam id);

    Map<String, String> findByTitles(Set<String> titles);

}