package vn.sapo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.sapo.customers.AddressService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerFilter;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.DataInputException;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
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
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy khách hàng"));
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
        addressService.deleteByCustomerId(id);
        customerRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    @Transactional
    public CustomerResult create(CreateCustomerParam createParam) {

//        Instant birthday = createCustomerParam.getBirthday().toInstant();
//        String cusCode =createCustomerParam.getCustomerCode();
//        String randomString = String.valueOf(Math.random()*1000000+1);
//        if(cusCode == null || cusCode.trim().isEmpty()){
//            createCustomerParam.setCustomerCode(randomString);
//        }
//        if(createCustomerParam.getFullName()==null){
//            throw new DataInputException("Tên khách hàng không được để trống");
//        }
//        Customer customer = customerMapper.toModel(createCustomerParam);
//        customer.setBirthday(birthday);
//        Customer customerResult = customerRepository.save(customer);
        CreateAddressParam createAddressParam = createParam.getCreateAddressParam();
        if (createAddressParam == null)
            throw new ValidationException(new HashMap<>() {{
                put("line1", "Dia chi khong duoc de trong");
            }});
        Customer customer = customerMapper.toModel(createParam);
        customer = customerRepository.save(customer);
        if (createParam.getCustomerCode() == null)
            customer.setCustomerCode(CodePrefix.CUSTOMER.generate(customer.getId()));
        createAddressParam.setCustomerId(customer.getId());
        addressService.create(createAddressParam);
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public CustomerResult update(UpdateCustomerParam updateCustomerParam) {
        Customer customer = customerRepository.findById(updateCustomerParam.getId())
                .orElseThrow(() -> new NotFoundException("Khách hàng không tồn tại hoặc đã bị xóa"));
        if (updateCustomerParam.getFullName().isEmpty() || updateCustomerParam.getFullName().equals("")) {
            updateCustomerParam.setFullName(customer.getFullName());
        }

        if (updateCustomerParam.getCustomerCode().equals("")) {
            updateCustomerParam.setCustomerCode(customer.getCustomerCode());
        }

        customerMapper.transferFields(updateCustomerParam, customer);

        Customer customerResult = customerRepository.save(customer);
        return customerMapper.toDTO(customerResult);
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
    @Transactional
    public void changeStatusToAvailable(List<Integer> customerIds, boolean status) {
        for (Integer customerId : customerIds) {
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
            customer.setStatus(status ? CustomerStatus.AVAILABLE : CustomerStatus.UNAVAILABLE);
        }
    }


    @Override
    @Transactional
    public List<CustomerResult> findAllByGroupListId(List<Integer> groupIds) {
        List<CustomerResult> customerResults = new ArrayList<>();
        customerResults = customerRepository.findAllByGroupIdIn(groupIds)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
        return customerResults;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResult> findAllByFilters(CustomerFilter filters, Pageable pageable) {
        return customerFilterRepository.findAllByFilters(filters, pageable).map(customerMapper::toDTO);
    }


}



