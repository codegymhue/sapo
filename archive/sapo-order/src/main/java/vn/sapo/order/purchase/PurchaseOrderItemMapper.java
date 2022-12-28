package vn.sapo.order.purchase;

import org.springframework.stereotype.Component;
import vn.sapo.entities.order.purchase.*;
import vn.sapo.order.purchase.dto.*;

@Component
public class PurchaseOrderItemMapper {

    public PurchaseOrderItemResult toDTO(PurchaseOrderItem purchaseOrderItem) {
        return new PurchaseOrderItemResult()
                .setId(purchaseOrderItem.getId())
                .setItemId(purchaseOrderItem.getItemId())
                .setPurchaseOrderId(purchaseOrderItem.getPurchaseOrderId())
                .setPrice(purchaseOrderItem.getPrice())
                .setProductId(purchaseOrderItem.getProductId())
                .setQuantity(purchaseOrderItem.getQuantity());
    }

    public PurchaseOrderItem toModel(PurchaseOrderItemParam purchaseOrderItemParam) {
        return new PurchaseOrderItem()
                .setItemId(purchaseOrderItemParam.getItemId())
                .setPurchaseOrderId(purchaseOrderItemParam.getPurchaseOrderId())
                .setProductId(purchaseOrderItemParam.getProductId());
    }
}
