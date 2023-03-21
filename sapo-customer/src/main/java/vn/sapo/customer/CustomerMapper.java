package vn.sapo.customer;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vn.sapo.customer.dto.CreateCustomerParam;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.customer.dto.UpdateCustomerParam;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;

import static vn.sapo.shared.configurations.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_DISABLED;
import static vn.sapo.shared.configurations.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_ENABLED;

@Component
public class CustomerMapper implements InitializingBean {
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_ENABLED)
    private ModelMapper modelMapper;
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_DISABLED)
    private ModelMapper modelMapperSkipNullDisabled;

    @Override
    public void afterPropertiesSet() throws Exception {
        TypeMap<CreateCustomerParam, Customer> createDTO2Model
                = modelMapper.createTypeMap(CreateCustomerParam.class, Customer.class);

        createDTO2Model.addMapping(source -> source.getBirthday().toInstant(), Customer::setBirthday);

        TypeMap<UpdateCustomerParam, Customer> updateDTO2Model
                = modelMapperSkipNullDisabled.createTypeMap(UpdateCustomerParam.class, Customer.class);

        updateDTO2Model.addMapping(source -> source.getBirthday().toInstant(), Customer::setBirthday);

        updateDTO2Model.addMappings(mapper -> {
            mapper.when(Conditions.isNotNull()).map(UpdateCustomerParam::getGroupId, Customer::setGroupId);
        });
    }

    public CustomerResult toDTO(Customer customer) {
            return modelMapper.map(customer, CustomerResult.class);
    }

    public Customer toModel(CreateCustomerParam createCustomerParam) {
        return modelMapper
                .map(createCustomerParam, Customer.class)
                .setStatus(CustomerStatus.AVAILABLE);
    }

    public void transferFields(UpdateCustomerParam updateCustomerParam, Customer customer) {
        modelMapperSkipNullDisabled.map(updateCustomerParam, customer);
    }

    public void transferFieldsSkipNull(UpdateCustomerParam updateCustomerParam, Customer customer) {
        modelMapper.map(updateCustomerParam, customer);
    }
}