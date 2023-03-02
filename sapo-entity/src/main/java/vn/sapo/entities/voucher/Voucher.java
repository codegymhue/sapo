// package vn.sapo.entities.voucher;

// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import lombok.experimental.Accessors;
// import vn.sapo.entities.*;
// import vn.sapo.entities.order.purchase.*;

// import javax.persistence.*;
// import java.math.BigDecimal;

// @Getter
// @Setter
// @NoArgsConstructor
// @Accessors(chain = true)
// @Entity
// @Table(name = "voucher")
// public class Voucher extends BaseEntity {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id", nullable = false)
//     private Integer id;
//     @Column(name = "voucher_code", unique = true)
//     private String voucherCode;

//     @Column(name = "account_id", nullable = false)
//     private Integer accountId;

//     @Column(name = "object_id", nullable = false)
//     private Integer objectId;

//     @Column(name = "object_type", nullable = false)
//     private Integer objectType;

//     @Column(name = "reference", nullable = false)
//     private Integer reference;

//     @Column(name = "amount")
//     private Integer amount;

//     @Column(name = "account_id", nullable = false)
//     private Integer accountId;

//     @Column(name = "note")
//     private Integer note;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "assignee id_id")
//     private Employee employee;

//     @Column(name = "employee_id", insertable = false, updatable = false)
//     private Integer employeeId;

//     @ManyToOne(fetch = FetchType.LAZY, optional = false)
//     @JoinColumn(name = "payment_method_id", foreignKey = @ForeignKey(name = "fk_voucher_payment_method"))
//     private PaymentMethod paymentMethod;

//     @Column(name = "payment_method_id", insertable = false, updatable = false)
//     private String paymentMethodId;
//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "purchase_order_id")
//     private PurchaseOrder purchaseOrder;

//     @Column(name = "purchase_order_id", insertable = false, updatable = false)
//     private Integer purchaseOrderId;

//     public Voucher(Integer id) {
//         this.id = id;
//     }

//     public Voucher setEmployeeId(Integer employeeId) {
//         this.employee = new Employee(this.employeeId = employeeId);
//         return this;
//     }

//     public Voucher setPaymentMethodId(String paymentMethodId) {
//         this.paymentMethod = new PaymentMethod(this.paymentMethodId = paymentMethodId);
//         return this;
//     }

//     public Voucher setPurchaseOrderId(Integer paymentMethodId) {
//         this.purchaseOrder = new PurchaseOrder(this.purchaseOrderId = paymentMethodId);
//         return this;
//     }

//     public Voucher(Integer employeeId, String paymentMethodId, Integer purchaseOrderId) {
//         setEmployeeId(employeeId);
//         setPaymentMethodId(paymentMethodId);
//         setPurchaseOrderId(purchaseOrderId);
//     }
// }
