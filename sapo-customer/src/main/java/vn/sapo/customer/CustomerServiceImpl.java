package vn.sapo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.address.AddressService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerFilter;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerFilterRepository customerFilterRepository;
    @Autowired
    private AddressService addressService;

    @Override
    @Transactional(readOnly = true)
    public CustomerResult findById(Integer id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy khách hàng"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResult> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void deleteById(Integer id) {
        addressService.deleteByCustomerId(id);
        customerRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public CustomerResult create(CreateCustomerParam createCustomerParam) {
        Customer customer = customerMapper.toModel(createCustomerParam);
        Customer newCustomer = customerRepository.save(customer);
        String cusCode = newCustomer.getCustomerCode();
        //TODO: save DB roi getFullName ko dc de trong la sao?
        if (createCustomerParam.getFullName() == null) {
            throw new DataInputException("Tên khách hàng không được để trống");
        }
        if (cusCode == null || cusCode.trim().isEmpty())
=======
    public CustomerResult create(CreateCustomerParam createParam) {
        Customer customer = customerMapper.toModel(createParam);
        customer = customerRepository.save(customer);
        if (createParam.getCustomerCode() == null)
>>>>>>> main
            customer.setCustomerCode(CodePrefix.CUSTOMER.generate(customer.getId()));
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public CustomerResult update(UpdateCustomerParam updateParam) {
        Customer customer = customerRepository.findById(updateParam.getId())
                .orElseThrow(() -> new NotFoundException("Khách hàng không tồn tại hoặc đã bị xóa"));
        customerMapper.transferFields(updateParam, customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public void changeStatusToAvailable(List<Integer> customerIds, boolean status) {
        for (Integer customerId : customerIds) {
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
            customer.setStatus(status ? CustomerStatus.AVAILABLE : CustomerStatus.UNAVAILABLE);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<CustomerResult> findAllByGroupListId(List<Integer> groupIds) {
        return customerRepository.findAllByGroupIdIn(groupIds)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResult> findAllEmployeeListId(List<Integer> employeeIds) {
        return null;
    }

    @Override
    public List<CustomerResult> findAllByGenderId(String genderId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResult> findAllByFilters(CustomerFilter filters, Pageable pageable) {
        return customerFilterRepository.
                findAllByFilters(filters, pageable)
                .map(customerMapper::toDTO);
    }


//    @Override
//    public List<CustomerResult> findAllByGroupId(Integer groupTitleId) {
//        List<CustomerResult> customerResults = new ArrayList<>();
//        customerResults = customerRepository.findAllByGroupId(groupTitleId)
//                .stream()
//                .map(customerMapper::toDTO)
//                .collect(Collectors.toList());
//        return customerResults;
//    }


    @Override
    @Transactional(readOnly = true)
    public List<CustomerResult> findAllCustomerByGroupAndStatus(Integer groupTitleId, String customerStatus) {
        List<CustomerResult> customerResults = new ArrayList<>();
        customerResults = customerRepository.findAllByGroupIdAndStatus(groupTitleId, CustomerStatus.parseCustomerGroup(customerStatus))
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());

        return customerResults;
    }

}




