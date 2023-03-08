package vn.sapo.payment_method;

import vn.sapo.payment_method.dto.CreatePaymentMethodParam;
import vn.sapo.payment_method.dto.PaymentMethodResult;
import vn.sapo.payment_method.dto.UpdatePaymentMethodParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.shared.exceptions.NotFoundException;

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
        return paymentMethodRepository.findById(id)
                .map(paymentMethodMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found paymentMethod with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentMethodResult findByTitle(String title) {
        return paymentMethodRepository.findByTitle(title)
                .map(paymentMethodMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found paymentMethod with title: " + title));
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