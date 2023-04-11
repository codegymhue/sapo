package vn.sapo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import vn.sapo.contact.ContactCustomerService;
import vn.sapo.contact.ContactMapper;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.customer.dto.*;
import vn.sapo.customers.AddressService;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerFilter;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.entities.Contact;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.excel.ExcelService;
import vn.sapo.mail.EMailSender;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private ExcelService excelService;
    @Autowired
    private ContactCustomerService contactCustomerService;
    @Autowired
    private EMailSender eMailSender;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public Set<ContactResult> getAllContactsByCustomerId(Integer customerId) {
        Customer customer = findCustomerById(customerId);
        Set<Contact> contacts = customer.getContacts();
        Set<ContactResult> contactResults = contacts.stream().map(contactMapper::toDTO).collect(Collectors.toSet());

        return contactResults;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResult findById(Integer id) {
                return customerMapper.toDTO(findCustomerById(id));
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
        Customer customer = findCustomerById(id);
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
        return customerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public List<CreateCustomerParam> excelToCustomerCreate(MultipartFile file) {
//        List<CreateCustomerParam> customers = excelService.save(file);
//        customers.forEach(param -> create(param));
        return excelService.save(file);
    }

    @Override
    @Transactional
    public CustomerResult update(Integer id, UpdateCustomerParam updateCustomerParam) {
        Customer customer = findCustomerById(id);

        String code = updateCustomerParam.getCustomerCode();
        if (!code.equalsIgnoreCase(customer.getCustomerCode()))
            validateCustomerCode(code);

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
        Customer customer = findCustomerById(customerId);
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

    @Override
    public void createSeriesCustomer(CreateSeriesCustomerParam createSeriesCustomerParam) {
        List<CreateCustomerParam> listResultParam = createSeriesCustomerParam.getListResultParam();
        HashMap<Integer, List<CreateAddressParam>> listObjAddress = createSeriesCustomerParam.getListObjAddress();
        Set<Integer> address = listObjAddress.keySet();
            for(int i=0; i<listResultParam.size(); i++){
                if(address.contains(i)){
                    createCustomerManyContact(listResultParam.get(i), listObjAddress.get(i));
                }else{
                    if(!listResultParam.get(i).getFullName().equals("")){
                        createCustomerOneContact(listResultParam.get(i));
                    }
                }
            }
        String contentEmail = createSeriesCustomerParam.getErrorMessage();
        String emailTo = createSeriesCustomerParam.getEmail();
        String emailFrom = createSeriesCustomerParam.getEmailFrom();
        String emailAppEmailPass = createSeriesCustomerParam.getPassAppEmailFrom();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String toDay = dateFormat.format(new Date());
        String subject = "Sapo - Nhập file khách hàng - "+ toDay;
        System.out.println("Subject: "+subject);
        System.out.println(createSeriesCustomerParam);
        eMailSender.sendEmail(emailTo, subject, contentEmail, emailFrom, emailAppEmailPass);

    }
    public void createCustomerOneContact(CreateCustomerParam createCustomerParam){
        System.out.println("one contact");
        CustomerResult customerResult = create(createCustomerParam);
        Integer customerId = customerResult.getId();
        if(createCustomerParam.getCustomerCode()==null) {
            Optional<Customer> customerById = customerRepository.findById(customerId);
            System.out.println(customerById.get());
            customerById.get().setCustomerCode(CodePrefix.CUSTOMER.generate(customerId));
            customerRepository.save(customerById.get());
        }
        CreateContactParam contact = new CreateContactParam();
        customerResult.setAddresses(addressService.findByCustomerId(customerId));
        contact.setFullName(customerResult.getAddresses().get(0).getFullName());
        contactCustomerService.createByCustomerId(customerId, contact);

    }
    public void createCustomerManyContact(CreateCustomerParam createCustomerParam, List<CreateAddressParam> listAddress){
        System.out.println("many contact");
        CustomerResult customerResult = create(createCustomerParam);
        Integer customerId = customerResult.getId();
        if(createCustomerParam.getCustomerCode()==null) {
            Optional<Customer> customerById = customerRepository.findById(customerId);
            customerById.get().setCustomerCode(CodePrefix.CUSTOMER.generate(customerId));
            customerRepository.save(customerById.get());
        }
        customerResult.setAddresses(addressService.findByCustomerId(customerId));
        CreateContactParam contact = new CreateContactParam();
        contact.setFullName(customerResult.getAddresses().get(0).getFullName());
        List<CreateContactParam> listContact = new ArrayList<>();
        listContact.add(contact);
        for(int i=0; i< listAddress.size(); i++){
            CreateAddressParam createAddressParam = listAddress.get(i);
            addressService.createAddressWithCustomerId(createAddressParam, customerId);
            CreateContactParam createContactParam = new CreateContactParam();
            createContactParam.setFullName(listAddress.get(i).getFullName());
            listContact.add(createContactParam);
        }
        for(int i=0; i< listContact.size(); i++){
            contactCustomerService.createByCustomerId(customerId, listContact.get(i));
        }
    }
    private Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer.findById.notFound"));
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



