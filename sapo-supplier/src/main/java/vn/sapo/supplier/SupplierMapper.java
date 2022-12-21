package vn.sapo.supplier;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.supplier.dto.CreateSupplierParam;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplier.dto.UpdateSupplierParam;

@Component
public class SupplierMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SupplierResult toDTO(Supplier supplier) {
        return modelMapper.map(supplier, SupplierResult.class);
    }


    public Supplier toModel(CreateSupplierParam createSupplierParam) {
        return modelMapper.map(createSupplierParam, Supplier.class);
    }

    public void transferFields(UpdateSupplierParam updateSupplierParam, Supplier supplier) {
        modelMapper.map(updateSupplierParam, supplier);
    }
}