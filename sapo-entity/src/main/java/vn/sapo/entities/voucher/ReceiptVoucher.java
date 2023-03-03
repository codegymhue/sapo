//package vn.sapo.entities.voucher;
//
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//import vn.sapo.entities.BaseEntity;
//import vn.sapo.entities.Employee;
//import vn.sapo.entities.PaymentMethod;
//import vn.sapo.entities.customer.Customer;
//import vn.sapo.entities.order.sale.SaleOrder;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Accessors(chain = true)
//@Entity
//@Table(name = "vo_receipt_voucher")
//public class ReceiptVoucher extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Column(name = "paid", nullable = false, precision = 10, scale = 2)
//    private BigDecimal paid;
//
//    @Column(name = "description", nullable = false, length = 200)
//    private String description;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
//
//    @Column(name = "employee_id", insertable = false, updatable = false)
//    private Integer employeeId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "payment_method_id")
//    private PaymentMethod paymentMethod;
//
//    @Column(name = "payment_method_id", insertable = false, updatable = false)
//    private String paymentMethodId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private SaleOrder order;
//
//    @Column(name = "order_id", insertable = false, updatable = false)
//    private Integer orderId;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//
//    @Column(name = "customer_id", insertable = false, updatable = false)
//    private Integer customerId;
//
//
//    public ReceiptVoucher setEmployeeId(Integer employeeId) {
//        this.employee = new Employee(this.employeeId = employeeId);
//        return this;
//    }
//
//    public ReceiptVoucher setCustomerId(Integer customerId) {
//        this.customer = new Customer(this.customerId = customerId);
//        return this;
//    }
//
//    public ReceiptVoucher setPaymentMethodId(String paymentMethodId) {
//        this.paymentMethod = new PaymentMethod(this.paymentMethodId = paymentMethodId);
//        return this;
//    }
//
//    public ReceiptVoucher setOrderId(Integer orderId) {
//        this.order = new SaleOrder(this.orderId = orderId);
//        return this;
//    }
//
//}
