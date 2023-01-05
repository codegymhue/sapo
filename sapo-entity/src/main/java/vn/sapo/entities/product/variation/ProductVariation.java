package vn.sapo.entities.product.variation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.product.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "product_variation")
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "mass")
    private float mass;

    @Column(name = "sku", length = 50, unique = true)
    private String sku;

    @Column(name = "bar_code", length = 50, unique = true)
    private String barCode;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "retail_price", precision = 10, scale = 2)
    private BigDecimal retailPrice;

    @Column(name = "import_price", precision = 10, scale = 2)
    private BigDecimal importPrice;

    @Column(name = "wholesale_price", precision = 10, scale = 2)
    private BigDecimal wholesalePrice;

    @Column(name = "cost_price", precision = 10, scale = 2)
    private BigDecimal costPrice;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name="fk_product_variation_product"))
    private Product product;

    public ProductVariation(Integer id) {
        this.id = id;
    }
}
