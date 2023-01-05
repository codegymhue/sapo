package vn.sapo.product_pricing_policy;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;
import vn.sapo.entities.product.pricing_policy.ProductPrice;
import vn.sapo.entities.product.pricing_policy.ProductPricingPolicy;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;
import vn.sapo.product_pricing_policy.dto.ProductPriceResult;

@Component
public class ProductPricingPolicyMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ProductPriceResult toDTO(ProductPricingPolicy productPricingPolicy) {
        return modelMapper.map(productPricingPolicy, ProductPriceResult.class);
    }
}
