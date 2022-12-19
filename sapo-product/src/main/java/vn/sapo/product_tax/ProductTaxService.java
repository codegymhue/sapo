package vn.sapo.product_tax;

import vn.sapo.product_tax.dto.ProductTaxParam;
import vn.sapo.product_tax.dto.ProductTaxResult;

import java.util.List;

public interface ProductTaxService {
    List<ProductTaxResult> create(List<ProductTaxParam> productTaxParams, Integer productId);

    List<ProductTaxResult> findAllByProductId(Integer productId);
}
