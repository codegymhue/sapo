package vn.sapo.customer;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

import java.util.HashMap;

import static vn.sapo.entities.customer.Customer.*;

@Component
public class CustomerMapper implements InitializingBean {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        TypeMap<CreateCustomerParam, Customer> createDTO2Model = modelMapper.createTypeMap(CreateCustomerParam.class, Customer.class);
        createDTO2Model.addMapping(source -> source.getBirthday().toInstant(), Customer::setBirthday);
        TypeMap<UpdateCustomerParam, Customer> updateDTO2Model = modelMapper.createTypeMap(UpdateCustomerParam.class, Customer.class);
        updateDTO2Model.addMapping(source -> source.getBirthday().toInstant(), Customer::setBirthday);
    }

    public CustomerResult toDTO(Customer customer) {
        CustomerResult customerResult = modelMapper.map(customer, CustomerResult.class);

        if (customer.getAttributes() != null) {
            customerResult.setAttFax(customer.getAttributes().get(FAX_ATTRIBUTE_NAME));
            customerResult.setAttWebsite(customer.getAttributes().get(WEBSITE_ATTRIBUTE_NAME));
            customerResult.setAttTaxCode(customer.getAttributes().get(TAX_CODE_ATTRIBUTE_NAME));
        }
        return customerResult;
    }

    public Customer toModel(CreateCustomerParam createCustomerParam) {
        Customer customer = modelMapper.map(createCustomerParam, Customer.class)
                .setStatus(CustomerStatus.AVAILABLE);
        HashMap<String, String> attributes = null;
        if (createCustomerParam.getAttFax() != null || createCustomerParam.getAttTaxCode() != null || createCustomerParam.getAttWebsite() != null) {
            attributes = new HashMap<>();
            if(createCustomerParam.getAttFax()!=null){
                attributes.put(FAX_ATTRIBUTE_NAME, createCustomerParam.getAttFax());
            }
            if(createCustomerParam.getAttTaxCode()!=null){
                attributes.put(TAX_CODE_ATTRIBUTE_NAME, createCustomerParam.getAttTaxCode());
            }
            if(createCustomerParam.getAttWebsite()!=null){
                attributes.put(WEBSITE_ATTRIBUTE_NAME, createCustomerParam.getAttWebsite());
            }
        }
        customer.setAttributes(attributes);


        return customer;
    }

    public void transferFields(UpdateCustomerParam updateCustomerParam, Customer customer) {
        modelMapper.map(updateCustomerParam, customer);
        HashMap<String, String> attributes = null;
        if (updateCustomerParam.getAttFax() != null || updateCustomerParam.getAttTaxCode() != null || updateCustomerParam.getAttWebsite() != null) {
            attributes = new HashMap<>();
            if(updateCustomerParam.getAttFax()!=null){
                attributes.put(FAX_ATTRIBUTE_NAME, updateCustomerParam.getAttFax());
            }
            if(updateCustomerParam.getAttTaxCode()!=null){
                attributes.put(TAX_CODE_ATTRIBUTE_NAME, updateCustomerParam.getAttTaxCode());
            }
            if(updateCustomerParam.getAttWebsite()!=null){
                attributes.put(WEBSITE_ATTRIBUTE_NAME, updateCustomerParam.getAttWebsite());
            }
        }
        customer.setAttributes(attributes);
    }
}