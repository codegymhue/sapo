package vn.sapo.product_pricing_policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.product.pricing_policy.ProductPricingPolicy;
import vn.sapo.product_pricing_policy.dto.ProductPriceCreParam;
import vn.sapo.product_pricing_policy.dto.ProductPriceResult;
import vn.sapo.pricing_policy.PricingPolicyService;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductPricingPolicyServiceImpl implements ProductPricingPolicyService{
    @Autowired
    ProductPricingPolicyRepository productPricingPolicyRepository;

    @Autowired
    PricingPolicyService pricingPolicyService;

    @Autowired
    ProductPricingPolicyMapper productPricingPolicyMapper;

    @Override
    public List<ProductPriceResult> createAll(List<ProductPriceCreParam> productPriceCreParams, Product product) {
        int productId = product.getId();
        List<ProductPricingPolicy> entities = new ArrayList<>();
        if (product.isApplyTax()) {
            entities = productPriceCreParams.stream()
                    .map(param -> {
                        if (!pricingPolicyService.findByTitle(param.getPricePolicyTitle()).isPresent())
                            throw new NotFoundException("Pricing Policy not found");
                        return new ProductPricingPolicy(productId, pricingPolicyService.findByTitle(param.getPricePolicyTitle()).get().getId());
                    })
                    .collect(Collectors.toList());

        } else {
            entities = productPriceCreParams.stream()
                    .map(param -> {
                        return new ProductPricingPolicy(productId, pricingPolicyService.findByTitle(param.getPricePolicyTitle()).get().getId());
                    })
                    .collect(Collectors.toList());
        }
        return productPricingPolicyRepository.saveAll(entities).stream()
                .map(productPricingPolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductPriceResult> findAllByProductId(Integer productId) {
        return null;
    }

    @Override
    public void deleteAllByProductId(Integer productId) {

    }
}
