package vn.sapo.entities.product.pricing_policy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.product.Product;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "product_pricing_policy")
public class ProductPricingPolicy implements Serializable {

    @EmbeddedId
    private ProductPricingId id;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @Column(name = "pricing_policy_id", insertable = false, updatable = false)
    private Integer pricingPolicyId;

    @Column(name = "price")
    private BigDecimal price;

    public ProductPricingPolicy(Integer productId, Integer pricingPolicyId) {
        id = new ProductPricingId(this.productId = productId, this.pricingPolicyId = pricingPolicyId);
    }

    public PricingPolicy getPricingPolicy() {
        return id.getPricingPolicy();
    }

    public String getPricingPolicyTitle() {
        return id.getPricingPolicy().getTitle();
    }

    public PricingPolicyType getPricingPolicyType() {
        return id.getPricingPolicy().getPricingPolicyType();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductPricingPolicy)) return false;
        ProductPricingPolicy that = (ProductPricingPolicy) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
