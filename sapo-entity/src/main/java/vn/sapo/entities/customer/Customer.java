package vn.sapo.entities.customer;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import vn.sapo.entities.Address;
import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.Employee;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "customer")
@TypeDef(
        name = "extension_attributes",
        typeClass = JsonType.class)
@TypeDef(
        name = "tags",
        typeClass = JsonType.class)
public class Customer extends BaseEntity {

    public Customer(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "customer_code", unique = true)
    private String customerCode;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Instant birthday;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private CustomerGender gender;

    @Column(name = "description")
    private String description;

    @Type(type = "extension_attributes")
    @Column(name = "extension_attributes", columnDefinition = "JSON")
    private HashMap<String, String> attributes = new HashMap<>();

    @Type(type = "tags")
    @Column(name = "tags", nullable = false, columnDefinition = "JSON")
    private List<String> tags = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_group_id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer_customer_group"))
    private CustomerGroup group;

    @Column(name = "customer_group_id", insertable = false, updatable = false)
    private Integer groupId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer_employee"))
    private Employee employee;

    @Column(name = "employee_id", insertable = false, updatable = false)
    private Integer employeeId;

    public Customer setEmployeeId(Integer employeeId) {
        this.employee = new Employee(this.employeeId = employeeId);
        return this;
    }

    public Customer setGroupId(Integer groupId) {
        this.group = new CustomerGroup(this.groupId = groupId);
        return this;
    }

    @OneToMany(targetEntity = Address.class, mappedBy = "customer")
    private Set<Address> addresses;

    public Address getShippingAddress() {
        if (addresses == null)
            return null;
        Optional<Address> opt = addresses.stream()
                .filter(Address::isShippingAddress)
                .findFirst();
        return opt.orElse(null);
    }

    public Address getBillAddress() {
        if (addresses == null)
            return null;
        Optional<Address> opt = addresses.stream()
                .filter(Address::isReceiveBill)
                .findFirst();
        return opt.orElse(null);
    }

    public <T> T getAttributeValue(String key) {
        if (attributes == null)
            return null;
        return (T) attributes.get(key);
    }

    public final static String WEBSITE_ATTRIBUTE_NAME = "website";
    public final static String FAX_ATTRIBUTE_NAME = "fax";
    public final static String TAX_CODE_ATTRIBUTE_NAME = "taxCode";

    public String getWebsite() {
        return getAttributeValue(WEBSITE_ATTRIBUTE_NAME);
    }

    public String getFax() {
        return getAttributeValue(FAX_ATTRIBUTE_NAME);
    }

    public String getTaxCode() {
        return getAttributeValue(TAX_CODE_ATTRIBUTE_NAME);
    }

    public Customer setWebsite(String website) {
        attributes.put(WEBSITE_ATTRIBUTE_NAME, website);
        return this;
    }

    public Customer setFax(String fax) {
        attributes.put(FAX_ATTRIBUTE_NAME, fax);
        return this;
    }

    public Customer setTaxCode(String taxCode) {
        attributes.put(TAX_CODE_ATTRIBUTE_NAME, taxCode);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", customerCode='" + customerCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", status=" + status +
                ", gender=" + gender +
                ", description='" + description + '\'' +
                ", attributes=" + attributes +
                ", group=" + group +
                ", groupId=" + groupId +
                ", employee=" + employee +
                ", employeeId=" + employeeId +
                ", addresses=" + addresses +
                '}';
    }
}