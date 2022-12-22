package vn.sapo.purchaseOrderItem;

import org.springframework.stereotype.Service;

public interface PurchaseOrderItemService {
    int getQuantityPurchaseByProductIdAndOrderStatusCode(Integer productId, String orderStatusCode);
}
