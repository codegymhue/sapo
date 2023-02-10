package vn.sapo.entities.supplier;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.supplier.supplier_tag.Tag;

import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.Employee;
import vn.sapo.entities.payment.PaymentMethod;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "supplier")
@Accessors(chain = true)
public class Supplier extends BaseEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supplier_code", unique = true, length = 50)
    private String supplierCode;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SupplierStatus status;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "employee_id", updatable = false, insertable = false)
    private Integer employeeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @Column(name = "payment_method_id", updatable = false, insertable = false)
    private String paymentMethodId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sup_group_id")
    private SupplierGroup group;

    @Column(name = "sup_group_id", insertable = false, updatable = false)
    private Integer groupId;
    @ManyToMany(mappedBy = "suppliers")
    private Set<Tag> tags = new HashSet<>();

    public Supplier(Integer id) {
        this.id = id;
    }

    public Supplier(Integer employeeId, String paymentMethodId) {
        setEmployeeId(employeeId);
        setPaymentMethodId(paymentMethodId);
    }

    public Supplier setEmployeeId(Integer employeeId) {
        this.employee = new Employee(this.employeeId = employeeId);
        return this;
    }

    public Supplier setPaymentMethodId(String paymentMethodId) {
        this.paymentMethod = new PaymentMethod(this.paymentMethodId = paymentMethodId);
        return this;
    }

}