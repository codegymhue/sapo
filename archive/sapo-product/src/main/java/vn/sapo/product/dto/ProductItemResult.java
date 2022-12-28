package vn.sapo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.brand.dto.BrandResult;
import vn.sapo.category.dto.CategoryResult;
import vn.sapo.entities.product.ProductStatus;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductItemResult {
    private Integer id;
    private String image;
    private String title;
    private CategoryResult category;
    private BrandResult brand;
    private Integer available;
    private Integer inventory;
    private ProductStatus status;
    private Instant createAt;
    private Instant updateAt;
}
