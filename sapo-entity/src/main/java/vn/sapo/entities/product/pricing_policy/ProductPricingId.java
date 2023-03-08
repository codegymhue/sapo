package vn.sapo.entities.product.pricing_policy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.sapo.entities.product.Product;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
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
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name="fk_product_pricing_product"))
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pricing_policy_id", foreignKey = @ForeignKey(name="fk_product_pricing_pricing_policy"))
    private PricingPolicy pricingPolicy;

    public ProductPricingId(Integer productId, Integer pricingPolicyId) {
        this.product = new Product(productId);
        this.pricingPolicy = new PricingPolicy(pricingPolicyId);
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
