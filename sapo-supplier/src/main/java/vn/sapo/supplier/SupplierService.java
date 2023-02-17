package vn.sapo.supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierFilter;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import java.util.List;
import java.util.Map;

public interface SupplierService {

    List<SupplierResult> findAll();

    SupplierResult findById(Integer id);

    SupplierResult create(CreateSupplierParam createSupplierParam);

    SupplierResult update(UpdateSupplierParam updateSupplierParam);

    void deleteById(Integer id);

    Map<String, Object> getAllSupplierPage(Integer pageNo, Integer pageSize, String title,
                                               String status
                                              );
    @Transactional(readOnly = true)
    Map<String, Object> findAllByFilters2(SupplierFilter filter);


    Page<SupplierResult> findAllByFilters(SupplierFilter filter, Pageable pageable);
}