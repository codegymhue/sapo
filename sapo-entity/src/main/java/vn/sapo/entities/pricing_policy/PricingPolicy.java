package vn.sapo.entities.pricing_policy;

import javax.persistence.*;

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
