package vn.sapo.product_tax.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.tax.TaxType;

@Getter
@Setter
@Accessors(chain = true)
public class ProductTaxResult {
    private Integer productId;
    private Integer taxId;
    private TaxType taxType;
}
