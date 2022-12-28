package vn.sapo.order.purchase;


import vn.sapo.order.purchase.dto.CreatePurchaseOrderParam;
import vn.sapo.order.purchase.dto.PurchaseOrderResult;

import java.util.List;

public interface PurchaseOrderService {

    List<PurchaseOrderResult> findAll();

    PurchaseOrderResult create(CreatePurchaseOrderParam createPurchaseOrder);
}
