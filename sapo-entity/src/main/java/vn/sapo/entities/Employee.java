package vn.sapo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "birthday")
    private Instant birthday;

    @Column(name = "gender", nullable = false)
    private String gender;

    public Employee(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                '}';
    }
}