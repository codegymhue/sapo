package vn.sapo.customerGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customerGroup.dto.*;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;

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
    public CustomerGroupResult create(CreateCusGroupParam createCusGroupParam) {
//        String title = createCusGroupParam.getTitle().trim();
//        String cusGrpCode = createCusGroupParam.getCusGrpCode().trim();
//        String description = createCusGroupParam.getDescription().trim();
//        int discount = createCusGroupParam.getDiscount();
//
//        Map<String, String> errors = new HashMap<>();
//
//        checkTitleCustomerGroup(title, errors);
//
//        if (!cusGrpCode.isEmpty()) {
//            checkCusGrpCodeWhenNotEmpty(cusGrpCode, errors);
//        }
//
//        checkDescription(description, errors);
//        checkDiscount(discount, errors);
//
//        if (!errors.isEmpty()) {
//            throw new DataInputValidateException(errors);
//        }
//
//        String maxSystemCustomerGroupCode = getMaxSystemCustomerGroupCode();
//        createCusGroupParam.setCusGrpCode(maxSystemCustomerGroupCode);
//
//        createCusGroupParam.setCusGrpCode(cusGrpCode);
//new ValidationException(new HashMap<>(){{put("cusGrpCode","sdfsdfsdf");}});
        CustomerGroup customerGroup = customerGroupMapper.toModel(createCusGroupParam);
        customerGroup.setCusGrpType(FIXED);

        if (customerGroup.getCusGrpCode() == null) {
            customerGroup.setCusGrpCode(getMaxSystemCustomerGroupCode());
        }

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

    private void checkDiscount(int discount, Map<String, String> errors) {
        if (discount < 0 || discount > 100) {
            errors.put("discount", "Giá trị chiết khấu từ 0 đến 100");
        }
    }

    private void checkDescription(String description, Map<String, String> errors) {
        if (description.length() > 255) {
            errors.put("description", "Mô tả không được vượt quá 255 ký tự");
        }
    }

    private void checkCusGrpCodeWhenNotEmpty(String cusGrpCode, Map<String, String> errors) {
        String prefix = CodePrefix.CUSTOMER_GROUP.getValue();

        if (cusGrpCode.substring(0, 3).equalsIgnoreCase(prefix)) {
            errors.put("cusGrpCode", "Mã nhóm không được có tiền tố của hệ thống CTN");
        } else if (customerGroupRepository.existsByCusGrpCode(cusGrpCode)) {
            errors.put("cusGrpCode", "Mã nhóm khách hàng này đã tồn tại");
        } else if (cusGrpCode.length() > 50) {
            errors.put("cusGrpCode", "Mã nhóm khách hàng không được vượt quá 50 ký tự");
        }
    }

    public void checkTitleCustomerGroup(String title, Map<String, String> errors) {
        if (title.isEmpty()) {
            errors.put("title", "Tên nhóm khách hàng không được để trống");
        } else if (title.length() > 50) {
            errors.put("title", "Tên nhóm khách hàng không được vượt quá 50 ký tự");
        }
    }

    String getMaxSystemCustomerGroupCode() {
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
