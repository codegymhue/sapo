package vn.sapo.entities.customer;

import lombok.*;
import lombok.experimental.Accessors;
import vn.sapo.entities.*;

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
public class Customer {

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer setEmployeeId(Integer employeeId) {
        this.employee = new Employee(this.employeeId = employeeId);
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "customer_code", nullable = false, length = 50)
    private String customerCode;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "customer_group", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerGroup customerGroup;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "birthday", nullable = false, length = 50)
    private String birthday;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerGender customerGender;

    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(targetEntity = Address.class, mappedBy = "customer")
    private Set<Address> addressSet;

    public Address getShippingAddress() {
        Optional<Address> opt = addressSet.stream()
                .filter(Address::isShippingAddress)
                .findFirst();
        return opt.orElse(null);
    }

    public Address getReceiveBillAddress() {
        Optional<Address> opt = addressSet.stream()
                .filter(Address::isReceiveBill)
                .findFirst();
        return opt.orElse(null);
    }

    @Column(name = "employee_id", insertable = false, updatable = false)
    private Integer employeeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    @PrePersist
    public void prePersist() {
        createdAt =Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt =Instant.now();
    }

}