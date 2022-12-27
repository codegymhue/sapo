package vn.sapo.order.sale.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.order.sale.SaleOrderItem;
import vn.sapo.entities.product.Product;

import java.util.List;

@Repository
public interface SaleOrderItemRepository extends JpaRepository<SaleOrderItem, Integer> {

    List<SaleOrderItem> findAllByOrderId(Integer id);


    Boolean existsByProductId(Integer productId);

    List<SaleOrderItem> findAllByProductId(Integer productId);

    List<SaleOrderItem> findAllByOrderIdAndProductId(Integer orderId, Product productId);

    int countSaleOrderItemByOrderId(Integer orderId);

    @Query("SELECT COUNT(oi) FROM SaleOrderItem  AS oi WHERE oi.id = :orderId")
    int countSaleOrderItemByOrderId(@Param("orderId") int orderId);

    @Query(value = "call sp_getCountItemByCustomerId(:id);", nativeQuery = true)
    Integer getQuantityItemCustomerOrderById(Integer id);
}
