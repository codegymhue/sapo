package vn.sapo.entities.tax;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "product_tax")
public class ProductTax implements Serializable {
    @EmbeddedId
    private ProductTaxId id;
    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @Column(name = "tax_id", insertable = false, updatable = false)
    private Integer taxId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type", insertable = false, updatable = false)
    private TaxType taxType;

    public ProductTax(Integer productId, Integer taxId, TaxType taxType) {
        id = new ProductTaxId(this.productId = productId, this.taxId = taxId, this.taxType = taxType);
    }

    public Tax getTax() {
        return id.getTax();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductTax)) return false;
        ProductTax that = (ProductTax) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
