package vn.sapo.supplier;

import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

import java.util.List;

public interface SupplierService {

    List<SupplierResult> findAll();

    SupplierResult findById(Integer id);

    SupplierResult create(CreateSupplierParam createSupplierParam);

    void update(UpdateSupplierParam createSupplierParam);

    void deleteById(Integer id);

}