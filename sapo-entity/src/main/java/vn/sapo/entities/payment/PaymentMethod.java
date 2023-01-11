package vn.sapo.entities.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    public PaymentMethod(String id) {
        this.id = id;
    }

}