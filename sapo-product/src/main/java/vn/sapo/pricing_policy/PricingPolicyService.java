package vn.sapo.pricing_policy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;

import java.util.List;

@Service
public interface PricingPolicyService {
    List<PricingPolicyResult> findAll();

    PricingPolicyResult findById(Integer id);

    void deleteById(Integer id);

    public List<PricingPolicyResult> findAllSale();

    public List<PricingPolicyResult> findAllPurchase();
}
