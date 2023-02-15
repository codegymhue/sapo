package vn.sapo.entities.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.Employee;
import vn.sapo.entities.payment.PaymentMethod;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;

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

    @Column(name = "cus_grp_type")
    @Enumerated(EnumType.STRING)
    private CustomerGroupType cusGrpType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @Column(name = "payment_method_id", insertable = false, updatable = false)
    private String paymentMethodId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricing_policy_id")
    private PricingPolicy pricingPolicy;

    @Column(name = "pricing_policy_id", insertable = false, updatable = false)
    private Integer pricingPolicyId;

    public CustomerGroup(Integer id) {
        this.id = id;
    }

    public CustomerGroup setPricingPolicyId(Integer pricingPolicyId) {
        this.pricingPolicy = new PricingPolicy(this.pricingPolicyId = pricingPolicyId);
        return this;
    }

    public CustomerGroup setPaymentMethodId(String paymentMethodId) {
        this.paymentMethod = new PaymentMethod(this.paymentMethodId = paymentMethodId);
        return this;
    }

}
