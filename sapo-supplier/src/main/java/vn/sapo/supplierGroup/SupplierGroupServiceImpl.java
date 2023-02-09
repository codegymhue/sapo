package vn.sapo.supplierGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

@Component
public class SupplierGroupServiceImpl implements SupplierGroupService{
    @Autowired
    SupplierGroupRepository supplierGroupRepository;
    @Override
    public SupplierGroupResult create(CreateSupGroupParam createSupGroupParam) {
        return null;
    }
}
