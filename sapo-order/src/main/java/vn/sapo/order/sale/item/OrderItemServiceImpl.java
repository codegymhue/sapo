package vn.sapo.order.sale.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.order.sale.SaleOrderItemMapper;
import vn.sapo.order.sale.dto.SaleOrderItemResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    SaleOrderItemMapper orderItemMapper;
    @Autowired
    SaleOrderItemRepository orderItemRepository;


    @Override
    public List<SaleOrderItemResult> findAll() {
        return null;
    }

    @Override
    public List<SaleOrderItemResult> findAllOrderItemByOrderId(Integer orderId) {
        return orderItemRepository.findAllByOrderId(orderId)
                .stream().map(orderItem -> orderItemMapper.toDTO(orderItem))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getQuantityItemCustomerOrderById(Integer id) {
        Integer quantityOrderByCustomerId = orderItemRepository.getQuantityItemCustomerOrderById(id);
        if (quantityOrderByCustomerId == null){
            return quantityOrderByCustomerId = 0;
        }
        return quantityOrderByCustomerId;
    }
}
