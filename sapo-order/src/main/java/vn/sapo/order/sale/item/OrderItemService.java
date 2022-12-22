package vn.sapo.order.sale.item;

import vn.sapo.order.sale.dto.SaleOrderItemResult;

import java.util.List;

public interface OrderItemService {

    List<SaleOrderItemResult> findAll();

    List<SaleOrderItemResult> findAllOrderItemByOrderId(Integer orderId);

    Integer getQuantityItemCustomerOrderById(Integer id);

}
