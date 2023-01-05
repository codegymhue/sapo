package vn.sapo.payment.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.payment.method.dto.CreatePaymentMethodParam;
import vn.sapo.payment.method.dto.PaymentMethodResult;
import vn.sapo.payment.method.dto.UpdatePaymentMethodParam;

import java.util.List;

@Service
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    public List<PaymentMethodResult> findAll() {
        return null;
    }

    @Override
    public PaymentMethodResult findById(Integer id) {
        return null;
    }

    @Override
    public PaymentMethodResult create(CreatePaymentMethodParam id) {
        return null;
    }

    @Override
    public PaymentMethodResult update(UpdatePaymentMethodParam id) {
        return null;
    }
}