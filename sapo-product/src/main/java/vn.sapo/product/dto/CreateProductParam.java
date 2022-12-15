package vn.sapo.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.media.dto.MediaParam;
import vn.sapo.product_tax.dto.ProductTaxParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CreateProductParam implements Serializable {
    private Integer id;
    private String title;
    private String unit;
    private String sku;
    private String description;
    private String barCode;
    private Integer quantity;
    private Float mass;
    private BigDecimal costPrice;
    private BigDecimal retailPrice;
    private BigDecimal importPrice;
    private BigDecimal wholesalePrice;
    private Integer categoryId;
    private Integer brandId;
    private boolean enableSell;
    private boolean applyTax;
    private boolean isTaxInclusive;
    private List<ProductTaxParam> taxList;
    private List<MediaParam> mediaList;
}
