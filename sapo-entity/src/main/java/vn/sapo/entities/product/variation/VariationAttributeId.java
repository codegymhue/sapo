package vn.sapo.entities.product.variation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.product.attribute.AttributeValue;
import vn.sapo.entities.tax.ProductTaxId;
import vn.sapo.entities.tax.Tax;
import vn.sapo.entities.tax.TaxType;

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
public class VariationAttributeId implements Serializable {
    @ManyToOne(optional = false)
    @JoinColumn(name = "variation_id", foreignKey = @ForeignKey(name="fk_variation_attribute_product_variation"))
    private ProductVariation productVariation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "attribute_value_id", foreignKey = @ForeignKey(name="fk_variation_attribute_attribute_value"))
    private AttributeValue attributeValue;

    public VariationAttributeId(Integer variationId, Integer attributeValueId) {
        productVariation = new ProductVariation(variationId);
        attributeValue = new AttributeValue(attributeValueId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariationAttributeId that = (VariationAttributeId) o;
        return Objects.equals(productVariation, that.productVariation) && Objects.equals(attributeValue, that.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productVariation, attributeValue);
    }
}
