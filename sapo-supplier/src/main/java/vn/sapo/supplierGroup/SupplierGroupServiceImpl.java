package vn.sapo.supplierGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.OperationException;
import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;
import vn.sapo.supplierGroup.dto.UpdateSupGroupParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierGroupServiceImpl implements SupplierGroupService {
    @Autowired
    SupplierGroupRepository supplierGroupRepository;

    @Autowired
    SupplierGroupMapper supplierGroupMapper;

    @Override
    @Transactional
    public SupplierGroupResult create(CreateSupGroupParam createParam) {

//        List<SupplierGroup> supplierGroupList = supplierGroupRepository.findAll();
//        System.out.println("Nhóm nhà cung cấp" + supplierGroupList);

//        for (int i = 0; i < supplierGroupList.size(); i++) {
//
//            if (createParam.getTitle().trim().equalsIgnoreCase(supplierGroupList.get(i).getTitle())
//            ) {
//                throw new NotFoundException("Group supplier exist");
//            }
//            if (createParam.getSupplierCode() != null) {
//                if (createParam.getSupplierCode().trim().equalsIgnoreCase(supplierGroupList.get(i).getSupGroupCode()))
//                    throw new NotFoundException("Group supplier code exist");
//            }
//        }
        if (supplierGroupRepository.existsByTitle(createParam.getTitle()))
            throw new OperationException("");
        if (supplierGroupRepository.existsBySupGroupCode(createParam.getSupGroupCode()))
            throw new OperationException("");
        SupplierGroup supplierGroup = supplierGroupMapper.toModel(createParam);
        supplierGroupRepository.save(supplierGroup);
        if (createParam.getSupGroupCode() == null)
            supplierGroup.setSupGroupCode(CodePrefix.SUPPLIER_GROUP.generate(supplierGroup.getId()));
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierGroupResult> finAll() {
        return supplierGroupRepository
                .findAll()
                .stream()
                .map(supplierGroupMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierGroupResult findById(Integer id) {
        SupplierGroup supplierGroup = supplierGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found group supplier with id: " + id));
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    @Transactional
    public SupplierGroupResult update(UpdateSupGroupParam updateParam) {
        SupplierGroup supplierGroup = supplierGroupRepository.findById(updateParam.getId())
                .orElseThrow(() -> new NotFoundException("Not found group supplier with id: " + updateParam.getId()));
        if (supplierGroupRepository.existsByTitle(updateParam.getTitle()))
            throw new OperationException("");
        if (updateParam.getSupGroupCode() != null && supplierGroupRepository.existsBySupGroupCode(updateParam.getSupGroupCode()))
            throw new OperationException("");

//        List<SupplierGroup> supplierGroupList = supplierGroupRepository.findAll();
//        for (int i = 0; i < supplierGroupList.size(); i++) {
//            if (updateParam.getTitle().trim().equalsIgnoreCase(supplierGroupList.get(i).getTitle())
//            ) {
//                supplierGroupMapper.transferFields(updateParam, supplierGroup);
//                return supplierGroupMapper.toDTO(supplierGroup);
//            }
//        }
        supplierGroupMapper.transferFields(updateParam, supplierGroup);
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    public void deleteById(Integer id) {
        supplierGroupRepository.deleteById(id);
    }
}
