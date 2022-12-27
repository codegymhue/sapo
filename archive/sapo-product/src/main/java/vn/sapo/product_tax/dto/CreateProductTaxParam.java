package vn.sapo.product_tax.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class CreateProductTaxParam extends ProductTaxParam {
    private Integer productId;
}
