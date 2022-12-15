package vn.sapo.tax;

import vn.sapo.entities.tax.Tax;
import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.tax.dto.TaxParam;
import vn.sapo.tax.dto.TaxResult;

import java.util.List;

public interface TaxService {

    List<TaxResult> findAll();

    Tax findById(Integer id);

    TaxResult create(TaxParam taxParam);

    List<TaxResult> findAllByProductId(List<ProductTaxResult> productTaxResults);
}
