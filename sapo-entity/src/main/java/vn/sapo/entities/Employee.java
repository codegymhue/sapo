package vn.sapo.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "employee")
@Accessors(chain = true)
public class Employee {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, length = 50, nullable = false)
    private String phone;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "birthday", length = 50, nullable = false)
    private String birthday;

    @Lob
    @Column(name = "gender", length = 50, nullable = false)
    private String gender;

    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Employee(Integer id) {
        this.id = id;
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