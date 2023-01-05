package vn.sapo.pricingPolicy;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.sapo.entities.pricing_policy.PricingPolicy;
import vn.sapo.pricingPolicy.dto.PricingPolicyResult;

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
