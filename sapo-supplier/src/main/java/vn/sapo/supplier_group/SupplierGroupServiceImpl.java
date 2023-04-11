package vn.sapo.supplier_group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;
import vn.sapo.supplier_group.dto.CreateSupGroupParam;
import vn.sapo.supplier_group.dto.SupplierGroupResult;
import vn.sapo.supplier_group.dto.UpdateSupGroupParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SupplierGroupServiceImpl implements SupplierGroupService {
    private final static String EXCEPTION_NOT_FOUND = "supplier_group.exception.notFound";
    @Autowired
    SupplierGroupRepository supplierGroupRepository;

    @Autowired
    SupplierGroupMapper supplierGroupMapper;

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
    public SupplierGroup findById(Integer id) {
        return supplierGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EXCEPTION_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierGroupResult getById(Integer id) {
        return supplierGroupMapper.toDTO(findById(id));
    }

    public void validationByTitle(String title) {
        if (supplierGroupRepository.existsByTitle(title))
            throw new ValidationException("title", "supplier_group.exception.title.existed");
    }

    public void validationBySupGroupCode(String supGroupCode) {
        String fieldName = "supGroupCode";
        if (supGroupCode.toUpperCase().startsWith(CodePrefix.SUPPLIER_GROUP.getValue())) {
            throw new ValidationException(fieldName, "supplier_group.exception.supGroupCode.prefix.invalid");
        }
        if (supplierGroupRepository.existsBySupGroupCode(supGroupCode))
            throw new ValidationException(fieldName, "supplier_group.exception.supGroupCode.existed");
    }

    @Override
    @Transactional
    public SupplierGroupResult create(CreateSupGroupParam createParam) {
        validationByTitle(createParam.getTitle());
        if (createParam.getSupGroupCode() != null)
            validationBySupGroupCode(createParam.getSupGroupCode());

        SupplierGroup supplierGroup = supplierGroupMapper.toModel(createParam);
        supplierGroupRepository.save(supplierGroup);
        supplierGroup.setSupGroupCode(CodePrefix.SUPPLIER_GROUP.generate(supplierGroup.getId()));
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    @Transactional
    public SupplierGroupResult update(UpdateSupGroupParam updateParam) {
        SupplierGroup supplierGroup = findById(updateParam.getId());

        String title = updateParam.getTitle();
        if (!supplierGroup.getTitle().equalsIgnoreCase(title))
            validationByTitle(title);

        String supGroupCode = updateParam.getSupGroupCode();
        if (supGroupCode != null && !supplierGroup.getSupGroupCode().equalsIgnoreCase(supGroupCode))
            validationBySupGroupCode(supGroupCode);

        supplierGroupMapper.transferFields(updateParam, supplierGroup);
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        supplierGroupRepository.deleteById(id);
    }

    @Override
    public Map<String, Integer> findAllByGroupCodes(Set<String> groupCodes) {
        return supplierGroupRepository.findBySupGroupCodeIn(groupCodes).stream()
                .collect(Collectors.toMap(SupplierGroup::getSupGroupCode, SupplierGroup::getId));
    }
}
