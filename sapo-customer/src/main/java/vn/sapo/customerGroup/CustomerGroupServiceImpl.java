package vn.sapo.customerGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customerGroup.dto.*;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.entities.customer.CustomerGroupType;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    @Autowired
    CustomerGroupFilterRepository customerGroupFilterRepository;

    @Override
    @Transactional
    public List<CustomerGroupResult> findAllCustomerGroupResult() {
        return customerGroupRepository.findAllCustomerGroupResult();
    }

    @Override
    @Transactional
    public CustomerGroupResult create(CreateCusGroupParam createParam) {
        String title = createParam.getTitle().trim();
        Map<Object, Object> errors = new HashMap<>();

        checkCustomerGroupTitle(title, errors);

        if (createParam.getCusGrpCode() != null) {
            String cusGrpCode = createParam.getCusGrpCode().trim();
            checkCusGrpCodeWhenNotEmpty(cusGrpCode, errors);
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

//        if (createCusGroupParam.getCusGrpCode() == null) {
//            createCusGroupParam.setCusGrpCode(getMaxSystemCustomerGroupCode());
//        }

//        customerGroup.setPaymentMethod(createCusGroupParam.getPaymentMethod());
//        customerGroup.setPricingPolicyId(createCusGroupParam.getPricingPolicyId());

//        CustomerGroup customerGroup = customerGroupMapper.toModel(createCusGroupParam);
//        //customerGroup.setCusGrpType(FIXED);
//
//        customerGroup = customerGroupRepository.save(customerGroup);
//
//        customerGroup.setCusGrpCode(CodePrefix.CUSTOMER_GROUP.generate(customerGroup.getId()));
//
//        return customerGroupMapper.toDTO(customerGroup);
        CustomerGroup customerGroup = customerGroupMapper.toModel(createParam);
        customerGroup.setType(CustomerGroupType.FIXED);
        customerGroup = customerGroupRepository.save(customerGroup);

        if (createParam.getCusGrpCode() == null)
            customerGroup.setCusGrpCode(CodePrefix.CUSTOMER_GROUP.generate(customerGroup.getId()));

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
    public List<ICustomerGroupResult> sortByGroup() {
        return customerGroupRepository.sortByGroup();
    }

    @Override
    @Transactional
    public CustomerGroupResult findById(Integer id) {
        return customerGroupRepository.findById(id)
                .map(customerGroupMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("customer.exception.notFound"));

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

    //TODO: ko can nua

    private void checkCustomerGroupTitle(String title, Map<Object, Object> errors) {
        if (customerGroupRepository.existsByTitle(title)) {
            errors.put("title", "{customer_group.validation.title.existed}");
        }
    }

    //TODO: Ko can nua

    public void checkCusGrpCodeWhenNotEmpty(String cusGrpCode, Map<Object, Object> errors) {
        String prefix = CodePrefix.CUSTOMER_GROUP.getValue();

        if (cusGrpCode.substring(0, 3).equalsIgnoreCase(prefix)) {
            errors.put("cusGrpCode", "{customer_group.validation.cusGrpCode.prefix}");
        }
    }

    //TODO: KO CAN THOIET
//    public String getMaxSystemCustomerGroupCode() {
//        String prefix = CodePrefix.CUSTOMER_GROUP.getValue();
//        String maxSystemCustomerGroupCode;
//
//        String currentMaxSystemCustomerGroupCode = customerGroupRepository.getMaxSystemCustomerGroupCode();
//
//        if (currentMaxSystemCustomerGroupCode == null) {
//            maxSystemCustomerGroupCode = CodePrefix.CUSTOMER_GROUP.getValue().concat("00001");
//        } else {
//
//            String[] a = currentMaxSystemCustomerGroupCode.split(prefix);
//            String suffix = a[1];
//
//            int currentCodeSuffix = Integer.parseInt(suffix);
//            currentCodeSuffix++;
//
//            if (currentCodeSuffix < 10) {
//                maxSystemCustomerGroupCode = prefix.concat("0000").concat(String.valueOf(currentCodeSuffix));
//            } else if (currentCodeSuffix < 100) {
//                maxSystemCustomerGroupCode = prefix.concat("000").concat(String.valueOf(currentCodeSuffix));
//            } else if (currentCodeSuffix < 1000) {
//                maxSystemCustomerGroupCode = prefix.concat("00").concat(String.valueOf(currentCodeSuffix));
//            } else if (currentCodeSuffix < 10000) {
//                maxSystemCustomerGroupCode = prefix.concat("0").concat(String.valueOf(currentCodeSuffix));
//            } else {
//                maxSystemCustomerGroupCode = prefix.concat(String.valueOf(currentCodeSuffix));
//            }
//        }
//
//        return maxSystemCustomerGroupCode;
//    }
}
