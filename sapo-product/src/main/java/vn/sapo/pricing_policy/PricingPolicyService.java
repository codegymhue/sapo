package vn.sapo.pricing_policy;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PricingPolicyService {
    List<PricingPolicyResult> findAll();

    PricingPolicyResult findById(Integer id);

    void deleteById(Integer id);
}
