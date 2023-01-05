package vn.sapo.pricing_policy;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;

@Component
public class PricingPolicyMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PricingPolicyResult toDTO(PricingPolicy pricingPolicy) {
        return modelMapper.map(pricingPolicy, PricingPolicyResult.class);
    }

    public PricingPolicy toModel(PricingPolicyResult pricingPolicyResult){
        return modelMapper.map(pricingPolicyResult,PricingPolicy.class);
    }
}
