package vn.sapo.entities.product.pricing_policy;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.CustomerGroup;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pricing_policy")
@Accessors(chain = true)
public class PricingPolicy {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pricing_policy_code", unique = true)
    private String pricingPolicyCode;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "pricing_policy_type")
    @Enumerated(EnumType.STRING)
    private PricingPolicyType pricingPolicyType;

    public PricingPolicy(Integer id) {
        this.id = id;
    }
}
