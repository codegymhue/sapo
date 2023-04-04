package vn.sapo.supplier_group;

import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.supplier_group.dto.CreateSupGroupParam;
import vn.sapo.supplier_group.dto.UpdateSupGroupParam;
import vn.sapo.supplier_group.dto.SupplierGroupResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SupplierGroupService {
    SupplierGroupResult create(CreateSupGroupParam createSupGroupParam);

    List<SupplierGroupResult> finAll();

    SupplierGroup findById(Integer id);
    SupplierGroupResult getById(Integer id);
    SupplierGroupResult update(UpdateSupGroupParam updateSupGroupParam);

    void deleteById(Integer id);


    Map<String, Integer> findAllByGroupCodes(Set<String> supGroupCodes);
}
