package vn.sapo.supplierGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierGroupServiceImpl implements SupplierGroupService{
    @Autowired
    SupplierGroupRepository supplierGroupRepository;

    @Autowired
    SupplierGroupMapper supplierGroupMapper;
    @Override
    public SupplierGroupResult create(CreateSupGroupParam createSupGroupParam) {
        SupplierGroup supplierGroup = supplierGroupMapper.toModel(createSupGroupParam);
        supplierGroupRepository.save(supplierGroup);
        if(supplierGroup.getSupplierCode() == null) {
            supplierGroup.setSupplierCode("STN" + supplierGroup.getId());
        }
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    public List<SupplierGroupResult> finAll() {
        return supplierGroupRepository
                .findAll()
                .stream()
                .map(supplierGroupMapper::toDTO)
                .collect(Collectors.toList());
    }
}
