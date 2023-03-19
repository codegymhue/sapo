package vn.sapo.supplier_group;

import vn.sapo.supplier_group.dto.CreateSupGroupParam;
import vn.sapo.supplier_group.dto.UpdateSupGroupParam;
import vn.sapo.supplier_group.dto.SupplierGroupResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SupplierGroupService {
    SupplierGroupResult create(CreateSupGroupParam createSupGroupParam);

    List<SupplierGroupResult> finAll();

    SupplierGroupResult findById(Integer id);

    SupplierGroupResult update(UpdateSupGroupParam updateSupGroupParam);

    void deleteById(Integer id);


    Map<String, Integer> findByGroupCodes(Set<String> supGroupCodes);
}