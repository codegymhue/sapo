package vn.sapo.customerGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.ICustomerGroup;
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
//TODO: CODE ben duoi la sao?
        // ID: 9
        String result = "CTN";
        String number = customerGroup.getId().toString();       // "9"
        int numberZero = 5 - number.length();
        String strNumberZero = "";
        for (int i = 0; i < numberZero; i++) {
            strNumberZero+= "0";

        }
        result += result + strNumberZero;
        System.out.println(result);
        if (customerGroup.getCusGrpCode() == null)
            customerGroup.setCusGrpCode(result);
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
        CustomerGroup customerGroup = customerGroupRepository.findById(id).get();
        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        customerGroupRepository.deleteById(id);
    }
}
