package vn.sapo.supplierGroup;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

@Component
public class SupplierGroupMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SupplierGroup toModel(CreateSupGroupParam createSupGroupParame) {
        return modelMapper.map(createSupGroupParame, SupplierGroup.class);
    }

    public SupplierGroupResult toDTO(SupplierGroup supplierGroup) {
        return modelMapper.map(supplierGroup, SupplierGroupResult.class);
    }
}
