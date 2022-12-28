package vn.sapo.entities.product.variation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.tax.ProductTaxId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "variation_attribute")
public class VariationAttribute implements Serializable {

    @EmbeddedId
    private VariationAttributeId id;

    @Column(name = "variation_id", insertable = false, updatable = false)
    private Integer variationId;

    @Column(name = "attribute_value_id", insertable = false, updatable = false)
    private Integer attributeValueId;
}
