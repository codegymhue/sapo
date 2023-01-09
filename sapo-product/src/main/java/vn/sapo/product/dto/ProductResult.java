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
import vn.sapo.tax.dto.TaxResult;

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
    private String barCode;
    private Float mass;
    private BigDecimal retailPrice;
    private BigDecimal importPrice;
    private BigDecimal wholesalePrice;
    private String description;
    private Integer categoryId;
    private Integer brandId;
    private CategoryResult category;
    private BrandResult brand;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean applyTax;
    private boolean taxInclusive;
    private List<TaxResult> saleTaxList;
    private List<TaxResult> purchaseTaxList;
    private List<MediaResult> mediaList;
    private int totalInventory;
    private int availableInventory;
    private int trading;
    private int inTransit;
    private int shipping;

}
