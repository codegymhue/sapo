package vn.sapo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.address.AddressService;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressService addressService;

    @Override
    @Transactional(readOnly = true)
    public CustomerResult findById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cutomer not found"));
        Integer customerId = customer.getId();
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResult> findAll() {
        List<CustomerResult> customerResults = new ArrayList<>();
        customerResults = customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO).collect(Collectors.toList());
        return customerResults;
    }


    @Override
    @Transactional
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }



    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    @Transactional
    public CustomerResult create(CreateCustomerParam createCustomerParam) {
        Customer customer = customerMapper.toModel(createCustomerParam);
       Customer newCustomer = customerRepository.save(customer);
        String cusCode = newCustomer.getCustomerCode();
        if (cusCode == null || cusCode.trim().isEmpty())
            customer.setCustomerCode(CodePrefix.CUSTOMER.generate(customer.getId()));
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public CustomerResult update(UpdateCustomerParam updateCustomerParam) {
        Customer customer = customerRepository.findById(updateCustomerParam.getId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        customerMapper.transferFields(updateCustomerParam, customer);
        return customerMapper.toDTO(customer);
    }

    //
//    @Override
//    @Transactional(readOnly = true)
//    public List<SaleOrderResult> findHistoryCustomerOrder(Integer id) {
//        List<SaleOrderResult> saleOrderByCustomer = saleOrderService.findAllSaleOrderByCustomerId(id);
//        return saleOrderByCustomer;
//    }
//
    @Override
    @Transactional(readOnly = true)
    public void changeStatusToAvailable(List<Integer> customerIds, boolean status) {
        for (Integer customerId : customerIds) {
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
            customer.setStatus(status ? CustomerStatus.AVAILABLE : CustomerStatus.UNAVAILABLE);
        }
    }


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



