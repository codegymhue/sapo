package vn.sapo.entities.pricing_policy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer_group")
@Accessors(chain = true)
public class PricingPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "pricing_policy_code", unique = true)
    private String pricingPolicyCode;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "pricing_policy_type")
    @Enumerated(EnumType.STRING)
    private PricingPolicyType pricingPolicyType;
}
