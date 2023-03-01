package vn.sapo.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.product.ProductStatus;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.supplier.dto.*;
import vn.sapo.supplierGroup.SupplierGroupRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierGroupRepository supplierGroupRepository;
    @Autowired
    private SupplierFilterRepository supplierFilterRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResult> findAll() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResult findById(Integer id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found supplier with id: " + id));
    }

    @Override
    @Transactional
    public SupplierResult create(CreateSupplierParam createParam) {
        Supplier supplier = supplierMapper.toModel(createParam);
        supplier.setEmployeeId(1);
        supplier = supplierRepository.save(supplier);
        if (createParam.getSupplierCode() == null)
            supplier.setSupplierCode(CodePrefix.SUPPLIER.generate(supplier.getId()));
        return supplierMapper.toDTO(supplier);
    }


    @Override
    @Transactional
    public SupplierResult update(UpdateSupplierParam param) {
        Supplier supplier = supplierRepository.findById(param.getId())
                .orElseThrow(() -> new NotFoundException("Not found supplier"));
//        if (param.getGroupId() != null && !supplierRepository.existsById(param.getGroupId()))
//            throw new NotFoundException("Group supplier exist");

//        if(supplier.getPhone().isEmpty() || supplier.getPhone().isBlank()){
//            supplier.setPhone(null);
//        }
//        if(supplier.getEmail().isEmpty() || supplier.getEmail().isBlank()) {
//            supplier.setEmail(null);
//        }
//        if (supplier.getDescription().isEmpty() || supplier.getDescription().isBlank()) {
//            supplier.setDescription(null);
//        }
//        if (supplier.getTaxCode().isEmpty() || supplier.getTaxCode().isBlank()) {
//            supplier.setTaxCode(null);
//        }
//        if(supplier.getFax().isEmpty() || supplier.getFax().isBlank()) {
//            supplier.setFax(null);
//        }
//        if(supplier.getWebsite().isEmpty() || supplier.getWebsite().isBlank()) {
//            supplier.setWebsite(null);
//        }
        supplierMapper.transferFields(param, supplier);
        return supplierMapper.toDTO(supplier);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        supplierRepository.deleteById(id);
    }


    @Override
    @Transactional
    public Map<String, Object> getAllSupplierPage(Integer pageNo,
                                                  Integer pageSize,
                                                  String status) {
        Page<Supplier> page;
        if (status.equals("")) {
            page = supplierRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
        } else {
            page = supplierRepository.findAllByStatus(SupplierStatus.parseSupplierStatus(status), PageRequest.of(pageNo - 1, pageSize));
        }
        if (page.hasContent()) {
            List<SupplierResult> dtoList = page.getContent()
                    .stream()
                    .map(supplierMapper::toDTO)
                    .collect(Collectors.toList());
            return new HashMap<>() {{
                put("suppliers", dtoList);
                put("totalItem", page.getTotalElements());
                put("totalPage", page.getTotalPages());
            }};
        } else {
            return new HashMap<>();
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Page<SupplierResult> findAllByFilters(SupplierFilter filter, Pageable pageable) {
        return supplierFilterRepository.findAllByFilters(filter, pageable).map(supplierMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> findAllByFilters(SupplierFilter filter) {
        if(filter.getPageNo() == null)
            filter.setPageNo(1);
        if(filter.getPageSize()== null)
            filter.setPageSize(20);
        Page<Supplier> page = supplierFilterRepository.findAllByFilters(filter, PageRequest.of(filter.getPageNo() - 1, filter.getPageSize()));

        if (page.hasContent()) {
            List<SupplierResult> dtoList = page.getContent()
                    .stream()
                    .map(supplierMapper::toDTO)
                    .collect(Collectors.toList());
            return new HashMap<>() {{
                put("suppliers", dtoList);
                put("totalItem", page.getTotalElements());
                put("totalPage", page.getTotalPages());
            }};
        } else {
            return new HashMap<>();
        }
    }

    @Override
    @Transactional()
    public void changeStatusToAvailable(Integer id, boolean status) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier not found"));
        supplier.setStatus(status ? SupplierStatus.AVAILABLE : SupplierStatus.UNAVAILABLE);
    }

    @Override
    @Transactional()
    public void changeEmpIdAndPaymentMethod(Integer supId, Integer empId, String paymentId) {
        Supplier supplier = supplierRepository.findById(supId)
                .orElseThrow(() -> new NotFoundException("Supplier not found"));

        if (empId != null)
            supplier.setEmployeeId(empId);
        if (paymentId != null)
        supplier.setPaymentMethodId(paymentId);
    }
}