package vn.sapo.payment.method;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.payment.PaymentMethod;
import vn.sapo.payment.method.dto.CreatePaymentMethodParam;
import vn.sapo.payment.method.dto.PaymentMethodResult;
import vn.sapo.payment.method.dto.UpdatePaymentMethodParam;

@Component
public class PaymentMethodMapper {

    @Autowired
    ModelMapper modelMapper;

    public PaymentMethodResult toDTO(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodResult.class);
    }

    public PaymentMethod toModel(CreatePaymentMethodParam createPaymentMethodParam) {
        return modelMapper.map(createPaymentMethodParam, PaymentMethod.class);
    }

    public PaymentMethod toModel(UpdatePaymentMethodParam updatePaymentMethodParam) {
        return modelMapper.map(updatePaymentMethodParam, PaymentMethod.class);
    }

}