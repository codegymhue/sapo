package vn.sapo.supplier;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    @Autowired
    private Configuration inheritingModelMapperConfiguration;

    @Override
    public void afterPropertiesSet() throws Exception {
        TypeMap<UpdateSupplierParam, Supplier> dto2Model = modelMapper.createTypeMap(UpdateSupplierParam.class, Supplier.class, inheritingModelMapperConfiguration);
        dto2Model.addMappings(mapper -> mapper.when(Conditions.isNotNull()).map(AbstractSupplierParam::getGroupId, Supplier::setGroupId));
        dto2Model.addMappings(mapper -> mapper.when(Conditions.isNotNull()).map(AbstractSupplierParam::getEmployeeId, Supplier::setEmployeeId));
        dto2Model.addMappings(mapper -> mapper.when(Conditions.isNotNull()).map(AbstractSupplierParam::getPaymentMethodId, Supplier::setPaymentMethodId));
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