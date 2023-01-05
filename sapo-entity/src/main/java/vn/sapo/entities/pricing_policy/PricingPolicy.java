package vn.sapo.entities.pricing_policy;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "pricing_policy")
public class PricingPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "pricing_policy_code", nullable = false)
    private String pricingPolicyCode;

    @Column(name = "pricing_policy_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PricingPolicyType pricingPolicyType;
}
