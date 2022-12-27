package vn.sapo.item;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import vn.sapo.entities.product.*;

import java.util.*;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByProductIdOrderByCreatedAt(Integer productId);

    List<Item> findAllByProductIdAndAvailableGreaterThanOrderByCreatedAt(Integer productId, Integer available);


    @Query(value = "call sp_getTotalInventoryQuantityByProductId(:productId)", nativeQuery = true)
    Optional<Integer> getTotalInventoryQuantityByProductId(@Param("productId") Integer productId);

    @Query(value = "call sp_getAvailableInventoryQuantityByProductId(:productId)", nativeQuery = true)
    Optional<Integer> getAvailableInventoryQuantityByProductId(@Param("productId") Integer productId);

    @Query(value = "call sp_getTradingQuantityByProductId(:productId)", nativeQuery = true)
    Optional<Integer> getTradingQuantityByProductId(@Param("productId") Integer productId);

    Optional <Item> findByProductId(Integer productId);
}
