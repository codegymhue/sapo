package vn.sapo.entities.supplier;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.Employee;
import vn.sapo.entities.payment.PaymentMethod;

import javax.persistence.*;
import java.util.HashMap;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "supplier")
@TypeDef(
        name = "json",
        typeClass = JsonType.class)
public class Supplier extends BaseEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supplier_code", unique = true, length = 50)
    private String supplierCode;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SupplierStatus status;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "fk_supplier_employee"))
    private Employee employee;

    @Column(name = "employee_id", updatable = false, insertable = false)
    private Integer employeeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", foreignKey = @ForeignKey(name = "fk_supplier_payment_method"))
    private PaymentMethod paymentMethod;

    @Column(name = "payment_method_id", updatable = false, insertable = false)
    private String paymentMethodId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_group_id", foreignKey = @ForeignKey(name = "fk_supplier_supplier_group"))
    private SupplierGroup group;

    @Column(name = "supplier_group_id", insertable = false, updatable = false)
    private Integer groupId;

    @Type(type = "json")
    @Column(name = "extension_attributes", nullable = false, columnDefinition = "JSON")
    private HashMap<String, String> attributes = new HashMap<>();

    public Supplier(Integer id) {
        this.id = id;
    }

    public Supplier(Integer employeeId, String paymentMethodId) {
        setEmployeeId(employeeId);
        setPaymentMethodId(paymentMethodId);
    }

    public Supplier setGroupId(Integer groupId) {
        this.group = new SupplierGroup(this.groupId = groupId);
        return this;
    }

    public Supplier setEmployeeId(Integer employeeId) {
        this.employee = new Employee(this.employeeId = employeeId);
        return this;
    }

    public Supplier setPaymentMethodId(String paymentMethodId) {
        this.paymentMethod = new PaymentMethod(this.paymentMethodId = paymentMethodId);
        return this;
    }

    public final static String WEBSITE_ATTRIBUTE_NAME = "website";
    public final static String FAX_ATTRIBUTE_NAME = "fax";
    public final static String TAX_CODE_ATTRIBUTE_NAME = "taxCode";

    public String getWebsite() {
        return attributes.get(WEBSITE_ATTRIBUTE_NAME);
    }

    public String getFax() {
        return attributes.get(FAX_ATTRIBUTE_NAME);
    }

    public String getTaxCode() {
        return attributes.get(TAX_CODE_ATTRIBUTE_NAME);
    }

    public Supplier setWebsite(String website) {
        attributes.put(WEBSITE_ATTRIBUTE_NAME, website);
        return this;
    }

    public Supplier setFax(String fax) {
        attributes.put(FAX_ATTRIBUTE_NAME, fax);
        return this;
    }

    public Supplier setTaxCode(String taxCode) {
        attributes.put(TAX_CODE_ATTRIBUTE_NAME, taxCode);
        return this;
    }
}