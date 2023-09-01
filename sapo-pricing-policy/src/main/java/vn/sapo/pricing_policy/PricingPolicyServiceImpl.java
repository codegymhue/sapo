package vn.sapo.pricing_policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;
import vn.sapo.entities.product.pricing_policy.PricingPolicyType;
import vn.sapo.pricing_policy.dto.PricingPolicyCreationParam;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;
import vn.sapo.pricing_policy.dto.PricingPolicyUpdateParam;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PricingPolicyServiceImpl implements PricingPolicyService {

    @Autowired
    private PricingPolicyMapper pricingPolicyMapper;

    @Autowired
    private PricingPolicyRepository pricingPolicyRepository;

    @Override
    public PricingPolicyResult create(PricingPolicyCreationParam param) {
        validateByPricingPolicyCode(param.getPricingPolicyCode());
        PricingPolicy pricingPolicy = pricingPolicyMapper.toEntity(param);
        pricingPolicyRepository.save(pricingPolicy);
        return pricingPolicyMapper.toDTO(pricingPolicy);
    }

    @Override
    public PricingPolicyResult update(PricingPolicyUpdateParam pricingPolicy) {
        return null;
    }

    @Override
    @Transactional
    public List<PricingPolicyResult> findAll() {
        return pricingPolicyMapper.toDTOList(pricingPolicyRepository.findAll());
    }

    @Override
    @Transactional
    public List<PricingPolicyResult> findAllSale() {
        return pricingPolicyRepository.findAll()
                .stream()
                .filter(pPricingPolicy -> pPricingPolicy.getPricingPolicyType() == PricingPolicyType.SALE)
                .map(pricingPolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PricingPolicyResult> findAllPurchase() {
        return pricingPolicyRepository.findAll()
                .stream()
                .filter(pricingPolicy -> pricingPolicy.getPricingPolicyType() == PricingPolicyType.PURCHASE)
                .map(pricingPolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PricingPolicyResult findById(Integer id) {
        PricingPolicy pricingPolicy = pricingPolicyRepository.findById(id).orElseThrow(
                () -> new NotFoundException("pricing_policy.exception.notFound"));
        return pricingPolicyMapper.toDTO(pricingPolicy);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Integer> findByTitles(Set<String> titles) {
        return pricingPolicyRepository.findByTitleIn(titles)
                .stream()
                .collect(Collectors.toMap(PricingPolicy::getTitle,PricingPolicy::getId));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        pricingPolicyRepository.deleteById(id);
    }

    private void validateByPricingPolicyCode(String pricingPolicyCode) {
        String fieldName = "pricingPolicyCode";
        if (pricingPolicyRepository.existsByPricingPolicyCode(pricingPolicyCode))
            throw new ValidationException(fieldName, "pricing_policy.exception.pricingPolicyCode.existed");
    }
}
