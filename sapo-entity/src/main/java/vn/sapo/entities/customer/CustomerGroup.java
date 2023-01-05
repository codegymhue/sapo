package vn.sapo.entities.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.payment.PaymentMethod;
import vn.sapo.entities.pricing_policy.PricingPolicy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer_group")
@Accessors(chain = true)
public class CustomerGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column(name = "cus_grp_code", nullable = false, length = 50)
    private String cusGrpCode;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "discount", nullable = false)
    private Integer discount;

    @OneToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @Column(name = "payment_method_id", nullable = false)
    private String payment_method_id;

    @OneToOne
    @JoinColumn(name = "pricing_policy_id")
    private PricingPolicy pricingPolicy;

    @Column(name = "pricing_policy_id", nullable = false)
    private Integer pricing_policy_id;

    public CustomerGroup(Integer id) {
        this.id = id;
    }


}
