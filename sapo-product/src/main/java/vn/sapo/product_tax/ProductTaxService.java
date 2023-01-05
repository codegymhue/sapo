package vn.sapo.product_tax;

import vn.sapo.entities.product.Product;
import vn.sapo.product_tax.dto.ProductTaxParam;
import vn.sapo.product_tax.dto.ProductTaxResult;

import java.util.List;

public interface ProductTaxService {
    List<ProductTaxResult> createAll(List<ProductTaxParam> productTaxParams, Product product);

    List<ProductTaxResult> findAllByProductId(Integer productId);

    void deleteAllByProductId(Integer productId);
}
