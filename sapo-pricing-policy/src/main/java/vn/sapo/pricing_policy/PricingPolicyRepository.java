package vn.sapo.pricing_policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PricingPolicyRepository extends JpaRepository<PricingPolicy, Integer> {

    boolean existsByTitle(String title);

    boolean existsByPricingPolicyCode(String pricingPolicyCode);

    List<PricingPolicy> findByTitleIn(Set<String> titles);
}
