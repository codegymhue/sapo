package vn.sapo.contact.dto;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vn.sapo.entities.Contact;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.supplier.dto.BaseSupplierParam;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import static vn.sapo.shared.configurations.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_DISABLED;
import static vn.sapo.shared.configurations.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_ENABLED;

@Component
public class ContactMapper {
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_ENABLED)
    private ModelMapper modelMapper;
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_DISABLED)
    private ModelMapper modelMapperSkipNullDisabled;


    public ContactResult toDTO(Contact contact) {
        return modelMapper.map(contact, ContactResult.class);
    }


    public Contact toModel(CreateContactParam createParam) {
        return modelMapper.map(createParam, Contact.class)
    }

    public void transferFields(UpdateContactParam updateParam, Contact supplier) {
        modelMapperSkipNullDisabled.map(updateParam, supplier);
    }


}