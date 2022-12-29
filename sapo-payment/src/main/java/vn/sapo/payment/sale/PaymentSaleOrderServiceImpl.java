package vn.sapo.payment.sale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.payment.sale.PaymentSaleOrder;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.order.sale.SaleOrderRepository;
import vn.sapo.payment.sale.dto.CreatePaymentSaleOrderParam;
import vn.sapo.payment.sale.dto.PaymentSaleOrderResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentSaleOrderServiceImpl implements PaymentSaleOrderService {

    @Autowired
    private PaymentSaleOrderMapper paymentOrderMapper;

    @Autowired
    private PaymentSaleOrderRepository paymentSaleOrderRepository;

    @Autowired
    private SaleOrderRepository orderRepository;


    @Override
    @Transactional(readOnly = true)
    public List<PaymentSaleOrderResult> findAll() {
        return paymentSaleOrderRepository.findAll()
                .stream()
                .map(paymentOrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentSaleOrderResult> findAllByOrderId(Integer orderId) {
        return paymentSaleOrderRepository.findAllByOrderId(orderId)
                .stream()
                .map(paymentOrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PaymentSaleOrderResult create(CreatePaymentSaleOrderParam paymentOrderParam) {
        System.out.println(paymentOrderParam.getOrderId());
        Integer orderId = paymentOrderParam.getOrderId();
        if (orderId != null && !orderRepository.existsById(orderId))
            throw new NotFoundException("Khong tim thay order can thanh toan");
        PaymentSaleOrder paymentSaleOrder = paymentOrderMapper.toModel(paymentOrderParam);
        paymentSaleOrder = paymentSaleOrderRepository.save(paymentSaleOrder);
        return paymentOrderMapper.toDTO(paymentSaleOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getPaidTotalByCustomerId(Integer id) {
        return paymentSaleOrderRepository.getPaidTotalByCustomerById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getDebtTotalByCustomerId(Integer customerId) {
        return paymentSaleOrderRepository.getDebtTotalByCustomerById(customerId);
    }
}