package vn.sapo.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierFilter;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;
import vn.sapo.supplierGroup.SupplierGroupRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return supplierRepository
                .findAll()
                .stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResult findById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found supplier with id: " + id));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public SupplierResult create(CreateSupplierParam createSupplierParam) {
        Supplier supplier = supplierMapper.toModel(createSupplierParam);
        supplier.setEmployeeId(1);
        supplier = supplierRepository.save(supplier);
        String supplierCode = createSupplierParam.getSupplierCode();
        if (supplierCode == null)
            supplier.setSupplierCode(CodePrefix.SUPPLIER.generate(supplier.getId()));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    @Transactional
    public SupplierResult  update(UpdateSupplierParam updateSupplierParam) {
//        System.out.println(updateSupplierParam.getId());
//        Optional<Supplier> supplier = supplierRepository.findById(updateSupplierParam.getId());
//        System.out.println(supplier.get());

        Supplier supplier = supplierRepository.findById(updateSupplierParam.getId())
                .orElseThrow(() -> new NotFoundException("Not found supplier"));
        SupplierGroup supplierGroup = supplierGroupRepository.findById(updateSupplierParam.getGroupId()).get();

        System.out.println(supplier);
        supplierMapper.transferFields(updateSupplierParam, supplier);
        supplier.setGroup(supplierGroup);
        Supplier supplierResult = supplierRepository.save(supplier);
        return supplierMapper.toDTO(supplierResult);

    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        findById(id);
        supplierRepository.deleteById(id);
    }


    @Override
    @Transactional
    public Map<String, Object> getAllSupplierPage(Integer pageNo, Integer pageSize, String title,
                                                     String status
                                                    ) {
        System.out.println("response: "+pageNo);
        System.out.println("response: "+pageSize);
        System.out.println("response: "+title);
        System.out.println("response: "+status);
        pageNo = pageNo - 1;
        Pageable pageable;

        pageable = PageRequest.of(pageNo, pageSize);

        Page<Supplier> suppliers;
        suppliers = supplierRepository.findAll(pageable);
        if (suppliers.hasContent()) {
            List<Supplier> supplierList = suppliers.getContent();
            List<SupplierResult> supplierResults = new ArrayList<>();
            for (Supplier supplier : supplierList) {
                SupplierResult supplierResult = supplierMapper.toDTO(supplier);

                supplierResults.add(supplierResult);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("suppliers", supplierResults);
            response.put("totalItem", suppliers.getTotalElements());
            response.put("totalPage", suppliers.getTotalPages());
            System.out.println("response: "+response);
            return response;
        } else {
            return new HashMap<>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SupplierResult> findAllByFilters(SupplierFilter filter, Pageable pageable) {
        return supplierFilterRepository.findAllByFilters(filter,pageable).map(supplierMapper::toDTO);
    }

}