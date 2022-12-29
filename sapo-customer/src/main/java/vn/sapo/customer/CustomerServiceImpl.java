package vn.sapo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.address.AddressService;
import vn.sapo.configurations.CodePrefix;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    public CustomerResult create(CreateCustomerParam createCustomerParam) {
        System.out.println("Đay là param" + createCustomerParam);
        Customer customer = customerMapper.toModel(createCustomerParam);
        customer = customerRepository.save(customer);
        if (customer.getCustomerCode() == null)
            customer.setCustomerCode(CodePrefix.CUSTOMER + CodePrefix.format(customer.getId()));
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
//    @Override
//    @Transactional(readOnly = true)
//    public List<CustomerDebtImpl> findCustomerDebtsByCustomerId(Integer customerId) {
//        List<CustomerDebt> customerDebts = customerRepository.findCustomerDebtsByCustomerId(customerId);
//
//        List<CustomerDebtImpl> customerDebts1 = customerDebts.stream().map(customerDebt -> {
//            CustomerDebtImpl customerDebtImpl = new CustomerDebtImpl();
//            customerDebtImpl.setFromICustomerOwer(customerDebt);
//            return customerDebtImpl;
//        }).collect(Collectors.toList());
//        BigDecimal tam = BigDecimal.valueOf(0);
//        for (CustomerDebtImpl customerDebtImpl : customerDebts1) {
//            tam = tam.add(customerDebtImpl.getTransaction());
//            customerDebtImpl.setTotalDebt(tam);
//            System.out.println(customerDebtImpl.getTransaction());
//        }
//        return customerDebts1;
//    }
//
//    @Override
//    public void deleteById(Integer customerId) {
//        Customer customer = customerRepository.findById(customerId).get();
//        try {
//            shippingAddressService.delete(customer.getShippingAddress().getId());
//            customerRepository.deleteById(customerId);
//        } catch (Exception e) {
//            throw new DataInputException("Lỗi không xác định");
//        }
//
//    }
    @Override
    @Transactional
    public void changeStatusToAvailable(List<Integer> customerIds, boolean status) {
        for (Integer customerId : customerIds) {
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Product not found"));
            customer.setStatus(status ? CustomerStatus.AVAILABLE : CustomerStatus.UNAVAILABLE);
        }
    }


}
