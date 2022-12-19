package vn.sapo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.brand.dto.BrandResult;
import vn.sapo.category.dto.CategoryResult;
import vn.sapo.entities.product.ProductStatus;
import vn.sapo.media.dto.MediaResult;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductResult {
    private Integer id;
    private String title;
    private ProductStatus status;
    private String unit;
    private String sku;
    private BigDecimal retailPrice;
    private BigDecimal importPrice;
    private BigDecimal wholesalePrice;
    private String description;
    private Integer categoryId;
    private Integer brandId;
    private CategoryResult category;
    private BrandResult brand;
    private Boolean applyTax;
    private Instant createdAt;
    private Instant updatedAt;
    private int totalInventory;
    private int availableInventory;
    private List<MediaResult> mediaList;
}
