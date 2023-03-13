package vn.sapo.order.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.order.sale.SaleOrder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {
    List<SaleOrder> findAllByCustomerId(Integer customerId);


    @Query(value = "call sp_getSpendTotalByCustomerId(:customerId);", nativeQuery = true)
    BigDecimal getSpendTotalByCustomerId(Integer customerId);

    @Query(value = "call sp_getCountOrderByCustomerId(:customerId);", nativeQuery = true)
    Integer getQuantityProductOrderById(Integer customerId);


    @Query("FROM SaleOrder WHERE customerId = :customerId")
    List<SaleOrder> findAllCustomerOrderHistory(Integer customerId);

    @Query(value = "call sp_getLastDayOrderByCustomerId(:customerId);", nativeQuery = true)
    Instant getLastDayOrderByCustomerId(Integer customerId);
}

