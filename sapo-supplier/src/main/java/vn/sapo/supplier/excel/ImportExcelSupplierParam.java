package vn.sapo.supplier.excel;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.contact.dto.CreateContactParam;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.supplier.dto.CreateSupplierParam;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ImportExcelSupplierParam extends CreateSupplierParam {
    private String paymentMethodTitle;
    private String defaultPricingPolicyTitle;
    private String supGroupCode;
    List<CreateAddressParam> addressList = new ArrayList<>();
    List<CreateContactParam> contactList = new ArrayList<>();
}
