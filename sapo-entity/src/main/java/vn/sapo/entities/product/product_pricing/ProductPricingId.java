package vn.sapo.entities.product.product_pricing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.sapo.entities.pricing_policy.PricingPolicy;
import vn.sapo.entities.product.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class ProductPricingId implements Serializable {
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pricing_id")
    private PricingPolicy pricingPolicy;

    public ProductPricingId(Integer productId, Integer pricingPolicyId) {
        product = new Product(productId);
        pricingPolicy = new PricingPolicy(pricingPolicyId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPricingId that = (ProductPricingId) o;
        return Objects.equals(product, that.product) && Objects.equals(pricingPolicy, that.pricingPolicy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, pricingPolicy);
    }
}
