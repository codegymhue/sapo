package vn.sapo.supplier;

import org.modelmapper.*;
import org.modelmapper.spi.DestinationSetter;
import org.modelmapper.spi.SourceGetter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.entities.Address;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.supplier.dto.AbstractSupplierParam;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

@Component
public class SupplierMapper implements InitializingBean {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        TypeMap<AbstractSupplierParam, Supplier> dto2Model = modelMapper.createTypeMap(AbstractSupplierParam.class, Supplier.class);
        dto2Model.addMappings(mapper -> {
            mapper.map(AbstractSupplierParam::getGroupId, Supplier::setGroupId);
            mapper.when(Conditions.isNotNull());
        });
        dto2Model.addMappings(mapper -> {
            mapper.map(AbstractSupplierParam::getEmployeeId, Supplier::setEmployeeId);
            mapper.when(Conditions.isNotNull());
        });
        dto2Model.addMappings(mapper -> {
            mapper.map(AbstractSupplierParam::getPaymentMethodId, Supplier::setPaymentMethodId);
            mapper.when(Conditions.isNotNull());
        });
    }

    public SupplierResult toDTO(Supplier supplier) {
        return modelMapper.map(supplier, SupplierResult.class);
    }


    public Supplier toModel(CreateSupplierParam createSupplierParam) {
        return modelMapper.map(createSupplierParam, Supplier.class)
                .setStatus(SupplierStatus.AVAILABLE);
    }

    public void transferFields(UpdateSupplierParam updateSupplierParam, Supplier supplier) {
        modelMapper.map(updateSupplierParam, supplier);
    }


}