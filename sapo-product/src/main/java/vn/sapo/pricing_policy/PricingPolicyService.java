package vn.sapo.pricing_policy;

import vn.sapo.entities.product.pricing_policy.PricingPolicy;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;
import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.tax.dto.CreateTaxParam;
import vn.sapo.tax.dto.TaxResult;

import java.util.List;
import java.util.Optional;

public interface PricingPolicyService {

    Optional<PricingPolicy> findByTitle(String title);

    boolean existsById(Integer id);

    TaxResult create(CreateTaxParam taxParam);

    public void createAll(List<CreateTaxParam> taxListParam);

    List<TaxResult> findAllByProductId(List<ProductTaxResult> productTaxResults);

    void deleteAllByProductId(Integer productId);
}
