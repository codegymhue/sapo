package vn.sapo.entities.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.PaymentMethod;
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

    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @Column(name = "default_discount_rate", nullable = false)
    private Integer defaultDiscountRate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CustomerGroupType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_payment_method_id", foreignKey = @ForeignKey(name = "fk_customer_group_payment_method"))
    private PaymentMethod paymentMethod;

    @Column(name = "default_payment_method_id", insertable = false, updatable = false)
    private String defaultPaymentMethodId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_pricing_policy_id", foreignKey = @ForeignKey(name = "fk_customer_group_pricing_policy"))
    private PricingPolicy pricingPolicy;

    @Column(name = "default_pricing_policy_id", insertable = false, updatable = false)
    private Integer defaultPricingPolicyId;

    public CustomerGroup(Integer id) {
        this.id = id;
    }

    public CustomerGroup setDefaultPricingPolicyId(Integer defaultPricingPolicyId) {
        this.pricingPolicy = new PricingPolicy(this.defaultPricingPolicyId = defaultPricingPolicyId);
        return this;
    }

    public CustomerGroup setDefaultPaymentMethodId(String defaultPaymentMethodId) {
        this.paymentMethod = new PaymentMethod(this.defaultPaymentMethodId = defaultPaymentMethodId);
        return this;
    }

}
