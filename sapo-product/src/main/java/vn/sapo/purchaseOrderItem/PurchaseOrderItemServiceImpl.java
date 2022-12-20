package vn.sapo.purchaseOrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService{
    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Override
    @Transactional(readOnly = true)
    public int getQuantityPurchaseByProductIdAndOrderStatusCode(Integer productId, String orderStatusCode) {
        return purchaseOrderItemRepository.getQuantityPurchaseByProductIdAndOrderStatusCode(productId, orderStatusCode).orElse(0);
    }
}
