package vn.sapo.pricing_policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;
import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.tax.dto.CreateTaxParam;
import vn.sapo.tax.dto.TaxResult;

import java.util.List;
import java.util.Optional;

@Service
public class PricingPolicyServiceImpl implements PricingPolicyService{
    @Autowired
    PricingPolicyRepository pricingPolicyRepository;

    @Autowired
    PricingPolicyMapper pricingPolicyMapper;

    @Override
    public Optional<PricingPolicy> findByTitle(String title) {
        return pricingPolicyRepository.findByTitle(title);
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public TaxResult create(CreateTaxParam taxParam) {
        return null;
    }

    @Override
    public void createAll(List<CreateTaxParam> taxListParam) {

    }

    @Override
    public List<TaxResult> findAllByProductId(List<ProductTaxResult> productTaxResults) {
        return null;
    }

    @Override
    public void deleteAllByProductId(Integer productId) {

    }
}
