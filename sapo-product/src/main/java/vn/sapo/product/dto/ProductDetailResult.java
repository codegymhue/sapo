package vn.sapo.product.dto;

import lombok.*;
import lombok.experimental.Accessors;
import vn.sapo.brand.dto.BrandResult;
import vn.sapo.category.dto.CategoryResult;
import vn.sapo.entities.product.ProductStatus;
import vn.sapo.item.dto.ItemResult;
import vn.sapo.media.dto.MediaResult;
import vn.sapo.tax.dto.TaxResult;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductDetailResult {

    private Integer id;
    private String title;
    private ProductStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private String description;
    private String unit;
    private String sku;
    private String barCode;
    private BigDecimal retailPrice;
    private BigDecimal importPrice;
    private BigDecimal wholesalePrice;
    private Float mass;
    private CategoryResult category;
    private BrandResult brand;
    private boolean applyTax;
    private boolean taxInclusive;
    private List<TaxResult> taxSaleList;
    private List<TaxResult> taxResults;
    private List<MediaResult> mediaResults;
    private ItemResult itemResult;
}
