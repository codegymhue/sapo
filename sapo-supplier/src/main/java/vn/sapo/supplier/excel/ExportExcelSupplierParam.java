package vn.sapo.supplier.excel;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.supplier.dto.CreateSupplierParam;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ExportExcelSupplierParam extends CreateSupplierParam {

    private String paymentMethodTitle;

    List<CreateAddressParam> createAddressParams = new ArrayList<>();

    private String supGroupCode;
}
