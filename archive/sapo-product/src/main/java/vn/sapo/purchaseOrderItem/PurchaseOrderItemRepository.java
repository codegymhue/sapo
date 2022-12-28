package vn.sapo.purchaseOrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.order.purchase.PurchaseOrderItem;

import java.util.Optional;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Integer> {
    @Query(value = "call sp_getQuantityPurchaseByProductIdAndOrderStatusCode(:productId, :orderStatusCode)", nativeQuery = true)
    Optional<Integer> getQuantityPurchaseByProductIdAndOrderStatusCode(@Param("productId") Integer productId, @Param("orderStatusCode") String orderStatusCode);
}
