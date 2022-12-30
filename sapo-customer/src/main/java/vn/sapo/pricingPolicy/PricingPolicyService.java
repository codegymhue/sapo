package vn.sapo.pricingPolicy;

import org.springframework.stereotype.Service;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.pricingPolicy.dto.PricingPolicyResult;

import java.util.List;

@Service
public interface PricingPolicyService {
    List<PricingPolicyResult> findAll();

    PricingPolicyResult findById(Integer id);

    void deleteById(Integer id);
}
