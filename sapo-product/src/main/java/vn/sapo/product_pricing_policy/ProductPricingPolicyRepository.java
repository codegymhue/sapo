package vn.sapo.product_pricing_policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.pricing_policy.ProductPricingId;
import vn.sapo.entities.product.pricing_policy.ProductPricingPolicy;

@Repository
public interface ProductPricingPolicyRepository extends JpaRepository<ProductPricingPolicy, ProductPricingId> {
}
