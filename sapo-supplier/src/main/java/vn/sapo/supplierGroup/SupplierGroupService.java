package vn.sapo.supplierGroup;

import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

public interface SupplierGroupService {
    SupplierGroupResult create (CreateSupGroupParam createSupGroupParam);
}
