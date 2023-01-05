package vn.sapo.product_pricing_policy;

import vn.sapo.entities.product.Product;
import vn.sapo.product_pricing_policy.dto.ProductPriceCreParam;
import vn.sapo.product_pricing_policy.dto.ProductPriceResult;

import java.util.List;

public interface ProductPricingPolicyService {

    List<ProductPriceResult> createAll(List<ProductPriceCreParam> productPriceCreParams, Integer productId);

    List<ProductPriceResult> findAllByProductId(Integer productId);

    void deleteAllByProductId(Integer productId);
}
