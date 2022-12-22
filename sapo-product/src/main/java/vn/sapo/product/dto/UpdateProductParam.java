package vn.sapo.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import vn.sapo.brand.dto.BrandResult;
import vn.sapo.category.dto.CategoryResult;
import vn.sapo.media.dto.MediaParam;
import vn.sapo.product_tax.dto.ProductTaxParam;
import vn.sapo.tax.dto.TaxResult;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateProductParam implements Serializable {
    private Integer id;
    private String title;
    private String unit;
    private String sku;
    private String description;
    private String barCode;
    private Float mass;
    private BigDecimal retailPrice;
    private BigDecimal importPrice;
    private BigDecimal wholesalePrice;
    private Integer categoryId;
    private Integer brandId;
    private CategoryResult category;
    private BrandResult brand;
    private boolean enableSell;
    private boolean applyTax;
    private boolean taxInclusive;
    private List<TaxResult> saleTaxList;
    private List<TaxResult> purchaseTaxList;
    private List<ProductTaxParam> taxList;
    private List<MediaParam> mediaList;
}