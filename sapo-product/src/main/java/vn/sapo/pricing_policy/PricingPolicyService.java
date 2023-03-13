package vn.sapo.pricing_policy;

import org.springframework.stereotype.Service;
import vn.sapo.pricing_policy.dto.PricingPolicyParam;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;

import java.util.List;

@Service
public interface PricingPolicyService {

    PricingPolicyResult create(PricingPolicyParam pricingPolicyParam);

    List<PricingPolicyResult> findAll();

    PricingPolicyResult findById(Integer id);

    void deleteById(Integer id);

    List<PricingPolicyResult> findAllSale();

    List<PricingPolicyResult> findAllPurchase();
}
