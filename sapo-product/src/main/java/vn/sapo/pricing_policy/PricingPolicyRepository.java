package vn.sapo.pricing_policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;

@Repository
public interface PricingPolicyRepository extends JpaRepository<PricingPolicy, Integer> {

}
