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
        CustomerResult dto = customerMapper.toDTO(customer);
//        dto.setSpendTotal(spendTotal);
//        dto.setDebtTotal(spendTotal.subtract(paidTotal));

//        Integer quantityProductOrder = saleOrderService.getQuantityProductOrder(customerResult.getId());
//        if (quantityProductOrder == null) {
//            quantityProductOrder = 0;
//        }
//        customerResult.setQuantityProductOrder(quantityProductOrder);
//        Integer quantityItemOrder = orderItemService.getQuantityItemCustomerOrderById(customerResult.getId());
//        if (quantityItemOrder == null) {
//            quantityItemOrder = 0;
//        }
//        customerResult.setQuantityItemOrder(quantityItemOrder);
//
//        Instant lastDayOrder = saleOrderService.getLastDayOrderByCustomerId(customerResult.getId());
//
//        customerResult.setLastDayOrder(lastDayOrder);

        return dto;
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
    @Transactional(readOnly = true)
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

<<<<<<< HEAD
//    @Override
//    public Map<String, Object> getAllCustomerItemPage(Integer pageNo, Integer pageSize, String code, String name, String phoneNumber, String group, BigDecimal debtTotal, BigDecimal spendTotal, int quantityItemOrder, String status, String typeSort, String nameFieldSort) {
//        return null;
//    }


//    @Override
//    @Transactional(readOnly = true)
//    public List<CustomerResult> findCustomerByStatus() {
//        return customerRepository.findCustomersByCustomerStatus()
//                .stream()
//                .map(customer -> {
//                    CustomerResult dto = customerMapper.toDTO(customer);
////                    BigDecimal spendTotal = saleOrderService.getSpendTotalByCustomerId(customer.getId());
////                    if (spendTotal == null)
////                        spendTotal = BigDecimal.valueOf(0);
////                    BigDecimal debtTotal = paymentSaleOrderService.getDebtTotalByCustomerId(customer.getId());
////                    if (debtTotal == null)
////                        debtTotal = BigDecimal.valueOf(0);
////                    dto.setSpendTotal(spendTotal);
////                    dto.setDebtTotal(debtTotal);
//                    return dto;
//                }).collect(Collectors.toList());
//    }

    @Override
    @Transactional
=======
    @Transactional(readOnly = true)
>>>>>>> order
    public CustomerResult create(CreateCustomerParam createCustomerParam) {
        Customer customer = customerMapper.toModel(createCustomerParam);
        customer = customerRepository.save(customer);
<<<<<<< HEAD
=======
        String cusCode = customer.getCustomerCode();
        if (cusCode == null || cusCode.trim().isEmpty())
            customer.setCustomerCode(CodePrefix.CUSTOMER + CodePrefix.format(customer.getId()));
>>>>>>> order
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
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
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Product not found"));
            customer.setStatus(status ? CustomerStatus.AVAILABLE : CustomerStatus.UNAVAILABLE);
        }
    }
<<<<<<< HEAD
}
=======


    @Override
    @Transactional(readOnly = true)
    public List<CustomerResult> findAllCustomerByGroupAndStatus(Integer groupTitleId, String customerStatus) {
        List<CustomerResult> customerResults = new ArrayList<>();
        customerResults = customerRepository.findAllCustomerByTitleContainingAndStatus(groupTitleId, CustomerStatus.parseCustomerGroup(customerStatus))
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
        return customerResults;
    }
    }



>>>>>>> order
