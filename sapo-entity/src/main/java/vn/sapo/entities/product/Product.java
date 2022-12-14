package vn.sapo.entities.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.tax.ProductTax;
import vn.sapo.entities.tax.Tax;
import vn.sapo.entities.tax.TaxType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "product")
public class   Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "image", nullable = false, length = 50)
    private String image;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "description")
    private String description;

    @Column(name = "unit", nullable = false, length = 50)
    private String unit;

    @Column(name = "sku", nullable = false, length = 50)
    private String sku;

    @Column(name = "bar_code", nullable = false, length = 50)
    private String barCode;

    @Column(name = "retail_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal retailPrice;

    @Column(name = "import_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal importPrice;

    @Column(name = "wholesale_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal wholesalePrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(name = "category_id", insertable = false, updatable = false)
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    @Column(name = "brand_id", insertable = false, updatable = false)
    private Integer brandId;

    @Column(name = "apply_tax", nullable = false, columnDefinition = "boolean default false")
    private Boolean applyTax = false;
    @Column(name = "is_tax_inclusive", nullable = false, columnDefinition = "boolean default false")
    private Boolean isTaxInclusive = false;
    @OneToMany(targetEntity = ProductTax.class, mappedBy = "id.tax")
    private Set<ProductTax> productTaxSet;

    public List<Tax> getTaxSale() {
        return productTaxSet.stream().filter(productTax -> productTax.getTaxType() == TaxType.TAX_SALE)
                .map(ProductTax::getTax).collect(Collectors.toList());
    }

    public List<Tax> getTaxPurchase() {
        return productTaxSet.stream().filter(productTax -> productTax.getTaxType() == TaxType.TAX_PURCHASE)
                .map(ProductTax::getTax).collect(Collectors.toList());
    }

    public Product(Integer categoryId, Integer brandId) {
        setCategoryId(categoryId);
        setBrandId(brandId);
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product setCategoryId(Integer categoryId) {
        this.category = new Category(this.categoryId = categoryId);
        return this;
    }

    public Product setBrandId(Integer brandId) {
        this.brand = new Brand(this.brandId = brandId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PrePersist
    public void prePersist() {
        createdAt =Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt =Instant.now();
    }
}