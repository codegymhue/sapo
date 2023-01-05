package vn.sapo.entities.product.pricing_policy;

<<<<<<<< HEAD:sapo-entity/src/main/java/vn/sapo/entities/product/pricing_policy/PricingPolicy.java
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pricing_policy")
@Accessors(chain = true)
========
import javax.persistence.*;

>>>>>>>> thien_dev:sapo-entity/src/main/java/vn/sapo/entities/pricing_policy/PricingPolicy.java
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
