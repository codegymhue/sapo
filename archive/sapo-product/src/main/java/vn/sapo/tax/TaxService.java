package vn.sapo.tax;

import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.tax.dto.*;

import java.util.List;

public interface TaxService {

    List<TaxResult> findAll();

    TaxResult findById(Integer id);

    boolean existsById(Integer id);

    TaxResult create(CreateTaxParam taxParam);

    public void createAll(List<CreateTaxParam> taxListParam);

    List<TaxResult> findAllByProductId(List<ProductTaxResult> productTaxResults);

    void deleteAllByProductId(Integer productId);
}
