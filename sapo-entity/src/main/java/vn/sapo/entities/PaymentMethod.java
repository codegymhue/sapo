package vn.sapo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payment_method")
@Accessors(chain = true)
public class PaymentMethod {
    @Id
    @Column(nullable = false, length = 50)
    private String id;

    @Column(name = "title", unique = true, nullable = false, length = 50)
    private String title;

    public PaymentMethod(String id) {
        this.id = id;
    }

}