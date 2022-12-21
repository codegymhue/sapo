package vn.sapo.payment.sale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.payment.sale.PaymentSaleOrder;
import vn.sapo.payment.sale.dto.CreatePaymentSaleOrderParam;
import vn.sapo.payment.sale.dto.PaymentSaleOrderResult;

@Component
public class PaymentSaleOrderMapper {
    @Autowired
    ModelMapper modelMapper;

    public PaymentSaleOrderResult toDTO(PaymentSaleOrder paymentOrder) {
        return modelMapper.map(paymentOrder, PaymentSaleOrderResult.class);
    }

    public PaymentSaleOrder toModel(CreatePaymentSaleOrderParam createPaymentSaleOrderParam) {
        return modelMapper.map(createPaymentSaleOrderParam, PaymentSaleOrder.class);
    }
}
