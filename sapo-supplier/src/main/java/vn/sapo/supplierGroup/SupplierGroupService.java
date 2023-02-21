package vn.sapo.supplierGroup;

import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.UpdateSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import java.util.List;

public interface SupplierGroupService {
    SupplierGroupResult create (CreateSupGroupParam createSupGroupParam);
    List<SupplierGroupResult> finAll();
    SupplierGroupResult findById(Integer id);

    SupplierGroupResult update(UpdateSupGroupParam updateSupGroupParam);
    void deleteById(Integer id);


}
