package vn.sapo.purchaseOrderItem;

public interface PurchaseOrderItemService {
    int getQuantityPurchaseByProductIdAndOrderStatusCode(Integer productId, String orderStatusCode);
}
