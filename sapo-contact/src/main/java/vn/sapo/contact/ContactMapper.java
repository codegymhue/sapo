package vn.sapo.contact;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vn.sapo.contact.dto.ContactResult;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.contact.dto.UpdateContactParam;
import vn.sapo.entities.Contact;

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
        return modelMapper.map(createParam, Contact.class);
    }

    public void transferFields(UpdateContactParam updateParam, Contact supplier) {
        modelMapperSkipNullDisabled.map(updateParam, supplier);
    }


}