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
    public List<ICustomerGroupResult> findAllCustomerGroupResult() {
        return customerGroupRepository.findAllCustomerGroupResult();
    }

    @Override
    @Transactional
    public CustomerGroupResult create(CreateCusGroupParam createParam) {
        String title = createParam.getTitle();

        validationByTitle(title);

        validationByCusGroupCode(createParam.getCusGrpCode());

        CustomerGroup customerGroup = customerGroupMapper.toModel(createParam);
        customerGroup.setType(CustomerGroupType.FIXED);
        customerGroup = customerGroupRepository.save(customerGroup);

        if (createParam.getCusGrpCode() == null)
            customerGroup.setCusGrpCode(CodePrefix.CUSTOMER_GROUP.generate(customerGroup.getId()));

        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public CustomerGroupResult update(Integer id, UpdateCusGroupParam updateCusGroupParam) {
        CustomerGroup customerGroup = customerGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer_group.exception.notFound"));

        String title = updateCusGroupParam.getTitle();

        if (!customerGroup.getTitle().equalsIgnoreCase(title)) {
            validationByTitle(title);
        }

        String cusGrpCode = updateCusGroupParam.getCusGrpCode();
        if (cusGrpCode != null && !customerGroup.getCusGrpCode().equalsIgnoreCase(cusGrpCode)) {
            validationByCusGroupCode(cusGrpCode);
        }

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
                .orElseThrow(() -> new NotFoundException("customer_group.exception.notFound"));

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

    private void validationByTitle(String title) {
        if (customerGroupRepository.existsByTitle(title)) {
            throw new ValidationException("title", "customer_group.validation.title.existed");
        }
    }

    public void validationByCusGroupCode(String cusGrpCode) {
        if (cusGrpCode.toUpperCase().startsWith(CodePrefix.CUSTOMER_GROUP.getValue())) {
            throw new ValidationException("cusGrpCode", "customer_group.validation.cusGrpCode.prefix");
        }
        if (customerGroupRepository.existsByCusGrpCode(cusGrpCode)) {
            throw new ValidationException("cusGrpCode", "customer_group.validation.cusGrpCode.existed");
        }
    }
}
