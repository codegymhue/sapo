package vn.sapo.payment_method;

import vn.sapo.payment_method.dto.CreatePaymentMethodParam;
import vn.sapo.payment_method.dto.PaymentMethodResult;
import vn.sapo.payment_method.dto.UpdatePaymentMethodParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.PaymentMethod;

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