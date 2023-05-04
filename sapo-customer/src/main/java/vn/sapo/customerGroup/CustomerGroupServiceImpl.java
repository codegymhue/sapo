package vn.sapo.customerGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customer.CustomerService;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customerGroup.dto.*;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public Page<ICustomerGroupResult> getAllCustomerGroupsPagination(DataTablesInput input) {
        int start = input.getStart();
        int length = input.getLength();
        int page = start / length + 1;

        Object sort = input.getOrder().get(0);
        LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) sort;
        String order = linkedHashMap.get("dir").toUpperCase();

        Sort s = Sort.by(Sort.Direction.valueOf(order), "title");

        Pageable pageable = PageRequest.of(page - 1, length, s);

        return findAllCustomerGroupPageable(pageable);
    }

    @Override
    public CustomerGroupResult findCustomerGroupByCustomerId(Integer id) {
        CustomerResult customerResult = customerService.findById(id);

        CustomerGroup customerGroup = customerGroupRepository
                .findCustomerGroupById(customerResult.getGroup().getId());

        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public Page<ICustomerGroupResult> findAllCustomerGroupPageable(Pageable pageable) {
        return customerGroupRepository.findAllCustomerGroupPageable(pageable);
    }

    @Override
    @Transactional
    public CustomerGroupResult create(CreateCusGroupParam createParam) {
        String title = createParam.getTitle();

        validationByTitle(title);

        if (createParam.getCusGrpCode() != null) {
            validationByCusGroupCode(createParam.getCusGrpCode());
        }

        CustomerGroup customerGroup = customerGroupMapper.toModel(createParam);
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
    @Transactional
    public CustomerGroupResult findById(Integer id) {
        return customerGroupRepository.findById(id)
                .map(customerGroupMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("customer_group.exception.notFound"));

    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        CustomerGroup customerGroup = findCustomerGroupById(id)
                .setDefaultPaymentMethodId(null)
                .setPaymentMethod(null)
                .setDefaultPricingPolicyId(null)
                .setPricingPolicy(null);

        List<Customer> customers = customerService.findAllByGroupId(id);
        customers.forEach(
                c -> c.setGroupId(null)
                        .setGroup(null)
        );

        customerGroupRepository.delete(customerGroup);
    }

    public void validationByTitle(String title) {
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

    public CustomerGroup findCustomerGroupById(Integer id) {
        return customerGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer_group.exception.notFound"));
    }
}
