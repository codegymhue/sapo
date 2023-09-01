package vn.sapo.pricing_policy;


import org.springframework.stereotype.Component;
import vn.rananu.shared.mappers.BaseMapper;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;
import vn.sapo.pricing_policy.dto.BasePricingPolicy;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;

@Component
public class PricingPolicyMapper extends BaseMapper<PricingPolicyResult, PricingPolicy, BasePricingPolicy> {

//    @Autowired
//    private ModelMapper modelMapper;
//
//    public PricingPolicyResult toDTO(PricingPolicy pricingPolicy) {
//        return modelMapper.map(pricingPolicy, PricingPolicyResult.class);
//    }
//
//    public PricingPolicy toModel(PricingPolicyResult pricingPolicyResult){
//        return modelMapper.map(pricingPolicyResult, PricingPolicy.class);
//    }
//
//    public PricingPolicy toModel(PricingPolicyParam pricingPolicyParam){
//        return new PricingPolicy()
//                .setTitle(pricingPolicyParam.getTitle())
//                .setPricingPolicyCode(pricingPolicyParam.getPricingPolicyCode())
//                .setPricingPolicyType(pricingPolicyParam
//                                .getPricingPolicyType());
//    }
}
