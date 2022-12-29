package vn.sapo.customerGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.UpdateCusGroupParam;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    @Override
    @Transactional
    public CustomerGroupResult create(CreateCusGroupParam createCusGroupParam) {
        CustomerGroup customerGroup = customerGroupMapper.toModel(createCusGroupParam);
        customerGroup = customerGroupRepository.save(customerGroup);
        if (customerGroup.getTitle() == null)
            customerGroup.setTitle(CodePrefix.CUSTOMER + CodePrefix.format(customerGroup.getId()));
        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public CustomerGroupResult update(UpdateCusGroupParam updateCusGroupParam) {
        CustomerGroup customerGroup = customerGroupRepository.findById(updateCusGroupParam.getId())
                .orElseThrow(()-> new NotFoundException("Customer Group not found"));
                customerGroupMapper.transferFields(updateCusGroupParam,customerGroup);
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
    public List<CustomerGroupResult> sortByGroup() {
        return customerGroupRepository.sortByGroup();
    }

    @Override
    public CustomerGroupResult findById(Integer id) {
        CustomerGroup customerGroup = customerGroupRepository.findById(id).get();
        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    public void deleteById(Integer id) {
        customerGroupRepository.deleteById(id);
    }
}
