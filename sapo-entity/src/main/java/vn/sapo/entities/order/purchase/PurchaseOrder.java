package vn.sapo.entities.order.purchase;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.fx.qh.sapo.entities.employee.Employee;
import vn.fx.qh.sapo.entities.order.OrderStatus;
import vn.fx.qh.sapo.entities.order.OrderStatusCode;
import vn.fx.qh.sapo.entities.product.supplier.Supplier;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "purchase_order_code", length = 45)
    private String purchaseOrderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "supplier_id", insertable = false, updatable = false)
    private Integer supplierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "employee_id", insertable = false, updatable = false)
    private Integer employeeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatusCode status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_status_code", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_status_code", nullable = false)
    private OrderStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status_code", insertable = false, updatable = false)
    private OrderStatusCode orderStatusCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status_code", insertable = false, updatable = false)
    private OrderStatusCode paymentStatusCode;

    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "grand_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal grandTotal;

    @Column(name = "discount", precision = 10, scale = 2)
    private Float discount;

    public PurchaseOrder(Integer supplierId, Integer employeeId) {
        setSupplierId(supplierId);
        setEmployeeId(employeeId);
    }

    public PurchaseOrder(Integer id) {
        this.id = id;
    }

    public PurchaseOrder setSupplierId(Integer supplierId) {
        this.supplier = new Supplier(this.supplierId = supplierId);
        return this;
    }

    public PurchaseOrder setEmployeeId(Integer employeeId) {
        this.employee = new Employee(this.employeeId = employeeId);
        return this;
    }

    @PrePersist
    public void prePersist() {
        createdAt =Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt =Instant.now();
    }

}