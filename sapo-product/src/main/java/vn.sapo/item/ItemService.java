package vn.sapo.item;

import vn.sapo.item.dto.*;

import java.util.*;

public interface ItemService {
    List<ItemResult> findAll();

    int getTotalInventoryQuantityByProductId(Integer productId);

    int getAvailableInventoryQuantityByProductId(Integer productId);

    int getTradingQuantityByProductId(Integer productId);

    ItemResult create(CreateItemParam CreateItemParam);

    ItemResult findAllByProductId(Integer productId);

}
