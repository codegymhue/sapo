package vn.sapo.customerGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customerGroup.dto.*;
import vn.sapo.entities.PaymentMethod;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.entities.product.pricing_policy.PricingPolicy;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static vn.sapo.entities.customer.CustomerGroupType.FIXED;

@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    private final CustomerGroupRepository customerGroupRepository;

    @Autowired
    CustomerGroupFilterRepository customerGroupFilterRepository;

    @Autowired
    public CustomerGroupServiceImpl(CustomerGroupRepository customerGroupRepository) {
        this.customerGroupRepository = customerGroupRepository;
    }

    @Override
    @Transactional
    public List<CustomerGroupResult> findAllCustomerGroupResult() {
        return customerGroupRepository.findAllCustomerGroupResult();
    }

    @Override
    @Transactional
    public CustomerGroupResult create(CreateCusGroupParam createCusGroupParam) {
        String title = createCusGroupParam.getTitle().trim();
        String description = createCusGroupParam.getDescription().trim();

        Map<Object, Object> errors = new HashMap<>();

        checkCustomerGroupTitle(title, errors);

        if (createCusGroupParam.getCusGrpCode() != null) {
            String cusGrpCode = createCusGroupParam.getCusGrpCode().trim();
            checkCusGrpCodeWhenNotEmpty(cusGrpCode, errors);
        }

        if (description.length() > 255) {
            errors.put("description", "Mô tả không được vượt quá 255 ký tự");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        if (createCusGroupParam.getCusGrpCode() == null) {
            createCusGroupParam.setCusGrpCode(getMaxSystemCustomerGroupCode());
        }

        CustomerGroup customerGroup = customerGroupMapper.toModel(createCusGroupParam);
        customerGroup.setCusGrpType(FIXED);

//        customerGroup.setPaymentMethod(createCusGroupParam.getPaymentMethod());
//        customerGroup.setPricingPolicyId(createCusGroupParam.getPricingPolicyId());

        customerGroup = customerGroupRepository.save(customerGroup);

        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public CustomerGroupResult update(UpdateCusGroupParam updateCusGroupParam) {
        CustomerGroup customerGroup = customerGroupRepository.findById(updateCusGroupParam.getId())
                .orElseThrow(() -> new NotFoundException("Customer Group not found"));
        customerGroupMapper.transferFields(updateCusGroupParam, customerGroup);
        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public List<CustomerGroupResult> findAll() {
        return customerGroupRepository.findAll()
                .stream()
                .map(customerGroupMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ICustomerGroup> sortByGroup() {
        return customerGroupRepository.sortByGroup();
    }

    @Override
    @Transactional
    public CustomerGroupResult findById(Integer id) {
        Optional<CustomerGroup> optionalCustomerGroup = customerGroupRepository.findById(id);

        if (optionalCustomerGroup.isEmpty()) {
            throw new NotFoundException("Customer id = " + id + "not found!");
        }

        CustomerGroup customerGroup = optionalCustomerGroup.get();

        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        customerGroupRepository.deleteById(id);
    }

//    @Override
//    public Page<CustomerGroup> findAllByFilters(CustomerGroupFilter filters, Pageable pageable) {
//        return customerGroupRepository.sortByGroup();
//    }

    @Override
    public Page<CustomerGroupResult> findAllByFilters(CustomerGroupFilter filters, Pageable pageable) {
        return customerGroupFilterRepository.
                findAllByFilters(filters, pageable)
                .map(customerGroupMapper::toDTO);
    }

    private void checkCustomerGroupTitle(String title, Map<Object, Object> errors) {

        if (title.length() > 250) {
            errors.put("title", "{customer.validation.CreateCusGroupParam.title.length}");
        }
        if (customerGroupRepository.existsByTitle(title)) {
            errors.put("title", "Nhóm khách hàng đã tồn tại");
        }
    }

    public void checkCusGrpCodeWhenNotEmpty(String cusGrpCode, Map<Object, Object> errors) {
        String prefix = CodePrefix.CUSTOMER_GROUP.getValue();

        if (cusGrpCode.substring(0, 3).equalsIgnoreCase(prefix)) {
            errors.put("cusGrpCode", "Mã nhóm không được có tiền tố của hệ thống CTN");
        } else if (customerGroupRepository.existsByCusGrpCode(cusGrpCode)) {
            errors.put("cusGrpCode", "Mã nhóm khách hàng này đã tồn tại");
        } else if (cusGrpCode.length() > 50) {
            errors.put("cusGrpCode", "Mã nhóm khách hàng không được vượt quá 50 ký tự");
        }
    }

    public String getMaxSystemCustomerGroupCode() {
        String prefix = CodePrefix.CUSTOMER_GROUP.getValue();
        String maxSystemCustomerGroupCode;

        String currentMaxSystemCustomerGroupCode = customerGroupRepository.getMaxSystemCustomerGroupCode();

        if (currentMaxSystemCustomerGroupCode == null) {
            maxSystemCustomerGroupCode = CodePrefix.CUSTOMER_GROUP.getValue().concat("00001");
        } else {

            String[] a = currentMaxSystemCustomerGroupCode.split(prefix);
            String suffix = a[1];

            int currentCodeSuffix = Integer.parseInt(suffix);
            currentCodeSuffix++;

            if (currentCodeSuffix < 10) {
                maxSystemCustomerGroupCode = prefix.concat("0000").concat(String.valueOf(currentCodeSuffix));
            } else if (currentCodeSuffix < 100) {
                maxSystemCustomerGroupCode = prefix.concat("000").concat(String.valueOf(currentCodeSuffix));
            } else if (currentCodeSuffix < 1000) {
                maxSystemCustomerGroupCode = prefix.concat("00").concat(String.valueOf(currentCodeSuffix));
            } else if (currentCodeSuffix < 10000) {
                maxSystemCustomerGroupCode = prefix.concat("0").concat(String.valueOf(currentCodeSuffix));
            } else {
                maxSystemCustomerGroupCode = prefix.concat(String.valueOf(currentCodeSuffix));
            }
        }

        return maxSystemCustomerGroupCode;
    }
}
