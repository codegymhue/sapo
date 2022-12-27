package vn.sapo.product_tax.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.tax.TaxType;

import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class ProductTaxParam implements Serializable {
    private Integer taxId;
    private TaxType taxType;
}
