package vn.sapo.entities.customer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.Address;
import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.Employee;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
@Accessors(chain = true)
public class Customer extends BaseEntity {
    public Customer(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;
    @Column(name = "\"group\"")
    @Enumerated(EnumType.STRING)
    private CustomerGroup group;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @Column(name = "employee_id", insertable = false, updatable = false)
    private Integer employeeId;

    public Customer setEmployeeId(Integer employeeId) {
        this.employee = new Employee(this.employeeId = employeeId);
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

}