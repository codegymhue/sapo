package vn.sapo.entities.tax;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fx.qh.sapo.entities.product.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class ProductTaxId implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tax_id")
    private Tax tax;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTaxId that = (ProductTaxId) o;
        return Objects.equals(product, that.product) && Objects.equals(tax, that.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, tax);
    }
}
