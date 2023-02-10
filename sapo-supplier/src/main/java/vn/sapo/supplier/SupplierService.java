package vn.sapo.supplier;

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

    Map<String, Object> getAllProductItemPage(Integer pageNo, Integer pageSize, String title,
                                               String status
                                              );


}