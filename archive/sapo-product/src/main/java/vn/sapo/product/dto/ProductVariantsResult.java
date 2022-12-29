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
public class ProductVariantsResult {
    private Integer id;
    private String image;
    private String title;
    private String sku;
    private String barCode;
    private CategoryResult category;
    private BrandResult brand;
    private ProductStatus status;
    private Instant createAt;
    private Instant updateAt;
    private boolean applyTax;
    private BigDecimal retailPrice;
    private BigDecimal importPrice;
    private BigDecimal wholesalePrice;
    private Integer available;
    private Integer inventory;
    private Integer trading;
    private Integer inTransit;
    private Integer shipping;
    
}
