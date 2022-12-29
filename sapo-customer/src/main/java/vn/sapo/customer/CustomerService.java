package vn.sapo.customer;

import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    CustomerResult findById(Integer id);

    List<CustomerResult> findAll();

    @Transactional(readOnly = true)
//    List<CustomerResult> findCustomerByStatus();

    CustomerResult create(CreateCustomerParam customerCreate);

    //
    CustomerResult update(UpdateCustomerParam updateCustomer);

//    List<SaleOrderResult> findHistoryCustomerOrder(Integer id);

//    List<CustomerDebtImpl> findCustomerDebtsByCustomerId(Integer customerId);

    void deleteById(Integer id);

    boolean existsById(Integer id);


//    Map<String, Object> getAllCustomerItemPage(Integer pageNo, Integer pageSize, String code, String name,
//                                               String phoneNumber, String group, BigDecimal debtTotal,
//                                               BigDecimal spendTotal, int quantityItemOrder
//                                            , String status,
//                                               String typeSort, String nameFieldSort);
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
    @Transactional
    void changeStatusToAvailable(List<Integer> customerIds, boolean status);
}
