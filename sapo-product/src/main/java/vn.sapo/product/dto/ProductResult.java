package vn.sapo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.brand.dto.BrandResult;
import vn.sapo.category.dto.CategoryResult;
import vn.sapo.entities.product.ProductStatus;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductResult {

    private Integer id;
    private String title;
    private ProductStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private String description;
    private String unit;
    private String sku;
    private String barCode;
    private Integer quantity;
    private BigDecimal retailPrice;
    private BigDecimal importPrice;
    private BigDecimal wholesalePrice;
    private Integer categoryId;
    private Integer brandId;
    private CategoryResult category;
    private BrandResult brand;
    private Boolean applyTax;
    private int totalInventory;
    private int availableInventory;
}
