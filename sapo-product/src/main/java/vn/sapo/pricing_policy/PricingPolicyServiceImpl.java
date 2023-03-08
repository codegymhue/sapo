package vn.sapo.pricing_policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;
import vn.sapo.entities.product.pricing_policy.PricingPolicyType;
import vn.sapo.pricing_policy.dto.PricingPolicyParam;
import vn.sapo.pricing_policy.dto.PricingPolicyResult;
import vn.sapo.shared.exceptions.DataInputValidateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PricingPolicyServiceImpl implements PricingPolicyService {

    @Autowired
    private PricingPolicyMapper pricingPolicyMapper;

    @Autowired
    private PricingPolicyRepository pricingPolicyRepository;

    @Override
    public PricingPolicyResult create(PricingPolicyParam pricingPolicyParam) {
        String title = pricingPolicyParam.getTitle().trim();
        String pricingPolicyCode = pricingPolicyParam.getPricingPolicyCode().trim();
        Map<String, String> errors = new HashMap<>();

        if (pricingPolicyParam.getPricingPolicyType() == null) {
            errors.put("pricingPolicyType", "Loại chính sách giá không được để trống");
        } else if (!pricingPolicyParam.getPricingPolicyType().trim().equalsIgnoreCase("sale")
                || !pricingPolicyParam.getPricingPolicyType().trim().equalsIgnoreCase("purchase")
        ) {
            errors.put("pricingPolicyType", "Loại chính sách không đúng định dạng");
        }

        if (title.isEmpty()) {
            errors.put("title", "Tên chính sách giá không được để trống");
        }
        if (pricingPolicyCode.isEmpty()) {
            errors.put("pricingPolicyCode", "Mã chính sách giá không được để trống");
        }

        if (!errors.isEmpty()) {
            throw new DataInputValidateException(errors);
        }

        PricingPolicy pricingPolicy = pricingPolicyMapper.toModel(pricingPolicyParam);

        pricingPolicyRepository.save(pricingPolicy);
        return pricingPolicyMapper.toDTO(pricingPolicy);
    }

    @Override
    @Transactional
    public List<PricingPolicyResult> findAll() {
        return pricingPolicyRepository.findAll()
                .stream()
                .map(pricingPolicyMapper::toDTO)
                .collect(Collectors.toList());
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
                .filter(pPricingPolicy -> pPricingPolicy.getPricingPolicyType() == PricingPolicyType.PURCHASE)
                .map(pricingPolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PricingPolicyResult findById(Integer id) {
        PricingPolicy pricingPolicy = pricingPolicyRepository.findById(id).get();
        return pricingPolicyMapper.toDTO(pricingPolicy);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        pricingPolicyRepository.deleteById(id);
    }
}
