package vn.sapo.supplier;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.exceptions.NotFoundException;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private SupplierRepository supplierRepository;


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
        supplier = supplierRepository.save(supplier);
        if (supplier.getSupplierCode() == null)
            supplier.setSupplierCode("SUPN" + supplier.getId());
        return supplierMapper.toDTO(supplier);
    }

    @Override
    @Transactional
    public void update(UpdateSupplierParam updateSupplierParam) {
        Supplier supplier = supplierRepository.findById(updateSupplierParam.getId())
                .orElseThrow(() -> new NotFoundException("Not found supplier"));
        supplierMapper.transferFields(updateSupplierParam, supplier);

    }


    @Override
    @Transactional
    public void deleteById(Integer id) {
        findById(id);
        supplierRepository.deleteById(id);
    }


}