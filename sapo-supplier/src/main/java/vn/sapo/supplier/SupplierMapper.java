package vn.sapo.supplier;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.supplier.dto.BaseSupplierParam;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import static vn.sapo.shared.configurations.MapperConfigure.MODEL_MAPPER_SKIP_NULL_DISABLED;
import static vn.sapo.shared.configurations.MapperConfigure.MODEL_MAPPER_SKIP_NULL_ENABLED;

@Component
public class SupplierMapper implements InitializingBean {
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_ENABLED)
    private ModelMapper modelMapper;
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_DISABLED)
    private ModelMapper modelMapperSkipNullDisabled;

    @Override
    public void afterPropertiesSet() throws Exception {
        TypeMap<UpdateSupplierParam, Supplier> dto2Model = modelMapperSkipNullDisabled.createTypeMap(UpdateSupplierParam.class, Supplier.class);
        dto2Model.addMappings(mapper -> {
            mapper.when(Conditions.isNotNull()).map(BaseSupplierParam::getGroupId, Supplier::setGroupId);
            mapper.when(Conditions.isNotNull()).map(BaseSupplierParam::getEmployeeId, Supplier::setEmployeeId);
            mapper.when(Conditions.isNotNull()).map(BaseSupplierParam::getPaymentMethodId, Supplier::setPaymentMethodId);
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
        modelMapperSkipNullDisabled.map(updateSupplierParam, supplier);
    }


}