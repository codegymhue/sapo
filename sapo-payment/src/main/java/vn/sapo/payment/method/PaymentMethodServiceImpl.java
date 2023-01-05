package vn.sapo.payment.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.payment.PaymentMethod;
import vn.sapo.payment.method.dto.CreatePaymentMethodParam;
import vn.sapo.payment.method.dto.PaymentMethodResult;
import vn.sapo.payment.method.dto.UpdatePaymentMethodParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    @Transactional
    public List<PaymentMethodResult> findAll() {
        return paymentMethodRepository.findAll()
                .stream()
                .map(paymentMethodMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentMethodResult findById(Integer id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).get();
        return paymentMethodMapper.toDTO(paymentMethod);
    }

    @Override
    @Transactional
    public PaymentMethodResult create(CreatePaymentMethodParam id) {
        return null;
    }

    @Override
    @Transactional
    public PaymentMethodResult update(UpdatePaymentMethodParam id) {
        return null;
    }
}