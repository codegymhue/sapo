package vn.sapo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.sapo.customer.dto.*;
import vn.sapo.customers.AddressService;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
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
    private CustomerFilterRepository customerFilterRepository;

    @Autowired
    private AddressService addressService;


    @Override
    @Transactional(readOnly = true)
    public CustomerResult findById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer.findById.notFound"));
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

        if (createParam.getCustomerCode() == null){
            createParam.setCustomerCode(CodePrefix.CUSTOMER.generate(createParam.getId()));
        }

//        Instant birthday = createParam.getBirthday().toInstant();
//        CreateAddressParam createAddressParam = createParam.getCreateAddressParam();
//        if (createAddressParam == null)
//            throw new ValidationException(new HashMap<>() {{
//                put("line1", "Dia chi khong duoc de trong");
//            }});
        Customer customer = customerMapper.toModel(createParam);
//        customer.setBirthday(birthday);
        customer = customerRepository.save(customer);

//        createAddressParam.setCustomerId(customer.getId());
//        addressService.create(createAddressParam);
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

    @Override
    @Transactional
    public CustomerResult updateSeries(CustomerUpdateSeries customerUpdateSeries) {
        System.out.println(customerUpdateSeries);
        Optional<Customer> customerOptional = customerRepository.findById(customerUpdateSeries.getCustomerId());
        if(customerOptional.isPresent()) {
            Integer employeeId = customerUpdateSeries.getEmployeeId();
            String paymentMethodId = customerUpdateSeries.getPaymentMethodId();
            Integer pricingPolicyId = customerUpdateSeries.getDefaultPrice();
            Integer id = customerUpdateSeries.getCustomerId();
            customerRepository.updateSeriesCustomer(employeeId, paymentMethodId, pricingPolicyId, id);
            Optional<Customer> customer = customerRepository.findById(id);
            return customerMapper.toDTO(customer.get());
        }else{
            throw new NotFoundException("Not Found");
        }
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



