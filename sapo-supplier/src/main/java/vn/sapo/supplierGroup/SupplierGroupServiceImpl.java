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
import java.util.Map;
import java.util.Set;
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
        if (createParam.getTitle() == null) {
            throw new OperationException("Tên nhóm nhà cung cấp không được để trống");
        }
        if (supplierGroupRepository.existsByTitle(createParam.getTitle()))
            throw new OperationException("Tên nhóm " + createParam.getTitle() + " đã tồn tại");

        if (createParam.getSupGroupCode() != null && supplierGroupRepository.existsBySupGroupCode(createParam.getSupGroupCode()))
            throw new OperationException("Mã nhóm nhà cung cấp đã tồn tại");

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

        if (supplierGroupRepository.existsBySupGroupCode(updateParam.getSupGroupCode()) && !supplierGroup.getSupGroupCode().equalsIgnoreCase(updateParam.getSupGroupCode())) {
            throw new OperationException("Mã nhóm nhà cung cấp đã tồn tại");
        }
        if (supplierGroupRepository.existsByTitle(updateParam.getTitle()) && !supplierGroup.getTitle().equalsIgnoreCase(updateParam.getTitle())) {
            throw new OperationException("Tên nhóm " + updateParam.getTitle() + " đã tồn tại");
        }
        if (updateParam.getSupGroupCode() != null && !supplierGroup.getSupGroupCode().equalsIgnoreCase(updateParam.getSupGroupCode())) {
            if (updateParam.getSupGroupCode().startsWith("STN")) {
                throw new OperationException("Mã không được có tiền tố của hệ thống STN");
            }
        }
        supplierGroupMapper.transferFields(updateParam, supplierGroup);
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    public void deleteById(Integer id) {
        supplierGroupRepository.deleteById(id);
    }

    @Override
    public Map<String, Integer> findByGroupCodes(Set<String> groupCodes) {
        return supplierGroupRepository.findBySupGroupCodeIn(groupCodes).stream()
                .collect(Collectors.toMap(SupplierGroup::getSupGroupCode, SupplierGroup::getId));
    }
}
