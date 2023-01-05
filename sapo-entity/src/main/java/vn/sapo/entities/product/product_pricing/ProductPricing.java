package vn.sapo.entities.product.product_pricing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.pricing_policy.PricingPolicy;
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
@Table(name = "product_pricing")
public class ProductPricing implements Serializable {

    @EmbeddedId
    private ProductPricingId id;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @Column(name = "pricing_id", insertable = false, updatable = false)
    private Integer productPricingId;

    @Column(name = "price_policy")
    private BigDecimal pricePolicy;

    public ProductPricing(Integer productId, Integer productPricingId) {
        id = new ProductPricingId(this.productId = productId, this.productPricingId = productPricingId);
    }

    public PricingPolicy getPricingPolicy() {
        return id.getPricingPolicy();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductPricing)) return false;
        ProductPricing that = (ProductPricing) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
