package vn.sapo.customerGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.sapo.customerGroup.dto.*;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static vn.sapo.entities.customer.CustomerGroupType.FIXED;

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
    public CustomerGroupResult create(CreateCusGroupParam createCusGroupParam) {
        String cusGrpCode = createCusGroupParam.getCusGrpCode().trim();

        if (createCusGroupParam.getTitle().trim().length() == 0) {
            throw new IllegalArgumentException("Tên nhóm khách hàng không được để trống!");
        }

        if (cusGrpCode.trim().length() == 0) {
            String maxSystemCustomerGroupCode = getMaxSystemCustomerGroupCode();

            createCusGroupParam.setCusGrpCode(maxSystemCustomerGroupCode);
        } else {
            if (customerGroupRepository.existsByCusGrpCode(cusGrpCode)) {
                throw new IllegalArgumentException("Tên nhóm khách hàng này đã tồn tại!");
            } else {
                createCusGroupParam.setCusGrpCode(cusGrpCode);
            }
        }

        CustomerGroup customerGroup = customerGroupMapper.toModel(createCusGroupParam);
        customerGroup.setCusGrpType(FIXED);

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

    private String getMaxSystemCustomerGroupCode() {
        String prefix = "CTN";
        String maxSystemCustomerGroupCode;

        String currentMaxSystemCustomerGroupCode = customerGroupRepository.getMaxSystemCustomerGroupCode();

        if (currentMaxSystemCustomerGroupCode == null) {
            maxSystemCustomerGroupCode = "CTN00001";
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
