package vn.sapo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import vn.sapo.customer.contact.dto.ContactParam;
import vn.sapo.customer.dto.*;
import vn.sapo.customerGroup.CustomerGroupRepository;
import vn.sapo.customers.AddressService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerFilter;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.excel.ExcelService;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

<<<<<<< HEAD
import java.nio.channels.MulticastChannel;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
=======
>>>>>>> vt_dev
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
    @Autowired
    private CustomerGroupRepository customerGroupRepository;
    @Autowired
    private ExcelService excelService;

    @Override
    @Transactional
    public CustomerResult createContact(Integer id, ContactParam contactParam) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer.findById.notFound"));

        //TODO: change attribute HashMap<String, String> --> HashMap<String, HashMap<String, String>
//        HashMap<String, HashMap<String, String>> contactsAttribute = new HashMap<>();
//        contactsAttribute.put("contacts", getContactParamValue(contactParam));
//
//        customer.setAttributes(contactsAttribute);

        customer.setAttributes(getContactParamValue(contactParam));

        Customer customerResult = customerRepository.save(customer);
        return customerMapper.toDTO(customerResult);
    }

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
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CustomerResult deleteById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("not found customer"));
        addressService.deleteByCustomerId(id);
        customerRepository.deleteById(id);
        return customerMapper.toDTO(customer);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    @Transactional
    public CustomerResult create(CreateCustomerParam createParam) {

        if (createParam.getGroupId() == null) {
            createParam.setGroupId(3);
        }

        if (createParam.getCustomerCode() != null)
            validateCustomerCode(createParam.getCustomerCode());

        Customer customer = customerMapper.toModel(createParam);
        customer = customerRepository.save(customer);
        if (createParam.getCustomerCode() == null)
            customer.setCustomerCode(CodePrefix.CUSTOMER.generate(customer.getId()));

        CreateAddressParam addressParam = createParam.getCreateAddressParam();
        addressParam.setCustomerId(customer.getId());

        if (createParam.getCreateAddressParam().getFullName() == null) {
            createParam.getCreateAddressParam().setFullName(createParam.getFullName());
        }

        addressService.create(addressParam);


//        if (createParam.getCreateAddressParam().getLine1() != null) {
//            if (createParam.getCreateAddressParam().getLine1().length() > 255)
//                throw new ValidationException("line1", "address.validation.line1.length");
//
//            CreateAddressParam addressParam = createParam
//                    .getCreateAddressParam()
//                    .setCustomerId(customer.getId());
//
//            addressService.create(addressParam);
//        }


//        Instant birthday = createParam.getBirthday().toInstant();
//        CreateAddressParam createAddressParam = createParam.getCreateAddressParam();
//        if (createAddressParam == null)
//            throw new ValidationException(new HashMap<>() {{
//                put("line1", "Dia chi khong duoc de trong");
//            }});
//        customer.setBirthday(birthday);

//        createAddressParam.setCustomerId(customer.getId());
//        addressService.create(createAddressParam);
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public List<CreateCustomerParam> createSeriesCustomerParam(MultipartFile file) {
//        List<CreateCustomerParam> customers = excelService.save(file);
//        customers.forEach(param -> create(param));
        return excelService.save(file);
    }

    @Override
    @Transactional
    public CustomerResult update(Integer id, UpdateCustomerParam updateCustomerParam) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer.findById.notFound"));

        String code = updateCustomerParam.getCustomerCode();
        if (!code.equalsIgnoreCase(customer.getCustomerCode()))
            validateCustomerCode(code);

//        if (updateCustomerParam.getFullName().isEmpty() || updateCustomerParam.getFullName().equals("")) {
//            updateCustomerParam.setFullName(customer.getFullName());
//        }

//        if (updateCustomerParam.getCustomerCode().equals("")) {
//            updateCustomerParam.setCustomerCode(customer.getCustomerCode());
//        }

//        customerMapper.transferFields(updateCustomerParam, customer);
        customerMapper.transferFieldsSkipNull(updateCustomerParam, customer);

        Customer customerResult = customerRepository.save(customer);
        return customerMapper.toDTO(customerResult);
    }

    @Override
    @Transactional
    public CustomerResult updateSeries(CustomerUpdateSeries customerUpdateSeries) {
        System.out.println(customerUpdateSeries);
        Optional<Customer> customerOptional = customerRepository.findById(customerUpdateSeries.getCustomerId());
        if (customerOptional.isPresent()) {
            Integer employeeId = customerUpdateSeries.getEmployeeId();
            String paymentMethodId = customerUpdateSeries.getPaymentMethodId();
            Integer pricingPolicyId = customerUpdateSeries.getDefaultPrice();
            Integer id = customerUpdateSeries.getCustomerId();
            customerRepository.updateSeriesCustomer(employeeId, paymentMethodId, pricingPolicyId, id);
            Optional<Customer> customer = customerRepository.findById(id);
            return customerMapper.toDTO(customer.get());
        } else {
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
    public CustomerResult changeStatusToAvailable(Integer customerId, boolean status) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
        customer.setStatus(status ? CustomerStatus.AVAILABLE : CustomerStatus.UNAVAILABLE);
        return customerMapper.toDTO(customerRepository.findById(customerId).get());
    }


    @Override
    @Transactional
    public List<CustomerResult> findAllByGroupListId(List<Integer> groupIds) {
        return customerRepository.findAllByGroupIdIn(groupIds)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResult> findAllByFilters(CustomerFilter filters, Pageable pageable) {
        return customerFilterRepository.findAllByFilters(filters, pageable).map(customerMapper::toDTO);
    }

    private HashMap<String, String> getContactParamValue(ContactParam contactParam) {
        HashMap<String, String> contacts = new HashMap<>();

        contacts.put("fullName", contactParam.getFullName());

        checkNullContactParamValue(contacts, "phoneNumber", contactParam.getPhoneNumber());
        checkNullContactParamValue(contacts, "email", contactParam.getEmail());
        checkNullContactParamValue(contacts, "fax", contactParam.getFax());
        checkNullContactParamValue(contacts, "position", contactParam.getPosition());
        checkNullContactParamValue(contacts, "department", contactParam.getDepartment());
        checkNullContactParamValue(contacts, "note", contactParam.getNote());

        contacts.put("createdAt", String.valueOf(Instant.now()));

        return contacts;
    }

    private void checkNullContactParamValue(HashMap<String, String> contacts, String field, String value) {
        if (value != null)
            contacts.put(field, value);
    }

    public void validateCustomerCode(String customerCode) {
        if (customerCode.toUpperCase().startsWith(CodePrefix.CUSTOMER.getValue())) {
            throw new ValidationException("customerCode", "customer.validation.customerCode.prefix");
        }
        if (customerRepository.existsCustomerByCustomerCode(customerCode)) {
            throw new ValidationException("customerCode", "customer.validation.customerCode.existed");
        }
    }

}



