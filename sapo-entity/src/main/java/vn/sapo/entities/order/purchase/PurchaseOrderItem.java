package vn.sapo.entities.order.purchase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.sapo.entities.product.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "purchase_order_item")
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_id", insertable = false, updatable = false)
    private Integer itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @Column(name = "purchase_order_id", insertable = false, updatable = false)
    private Integer purchaseOrderId;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    public PurchaseOrderItem(Integer productId, Integer purchaseOrderId, Integer itemId) {
        setItemId(itemId);
        setProductId(productId);
        setPurchaseOrderId(purchaseOrderId);
    }

    public PurchaseOrderItem(Integer id) {
        this.id = id;
    }

    public PurchaseOrderItem setProductId(Integer productId) {
        this.product = new Product(this.productId = productId);
        return this;
    }

    public PurchaseOrderItem setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrder = new PurchaseOrder(this.purchaseOrderId = purchaseOrderId);
        return this;
    }

    public PurchaseOrderItem setItemId(Integer itemId) {
        this.item = new Item(this.itemId = itemId);
        return this;
    }

}