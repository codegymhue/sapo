package vn.sapo.entities.order.sale;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.product.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "sale_order_item")
@Accessors(chain = true)
public class SaleOrderItem {
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
    @JoinColumn(name = "order_id")
    private SaleOrder order;

    @Column(name = "order_id", insertable = false, updatable = false)
    private Integer orderId;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "tax", nullable = false)
    private Float tax;

    @Column(name = "discount", precision = 10, scale = 2)
    private BigDecimal discount;


    public SaleOrderItem(Integer productId, Integer itemId, Integer orderId) {
        setProductId(productId);
        setItemId(itemId);
        setOrderId(orderId);
    }

    public SaleOrderItem setProductId(Integer productId) {
        this.product = new Product(this.productId = productId);
        return this;
    }

    public SaleOrderItem setItemId(Integer itemId) {
        this.item = new Item(this.itemId = itemId);
        return this;
    }

    public SaleOrderItem setOrderId(Integer orderId) {
        this.order = new SaleOrder(this.orderId = orderId);
        return this;
    }
}