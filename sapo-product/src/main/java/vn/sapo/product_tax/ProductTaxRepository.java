package vn.sapo.product_tax;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.tax.ProductTax;
import vn.sapo.entities.tax.ProductTaxId;
import vn.sapo.entities.tax.TaxType;

import java.util.List;

@Repository
public interface ProductTaxRepository extends JpaRepository<ProductTax, ProductTaxId> {
    List<ProductTax> findAllByProductIdAndTaxType(Integer productId, TaxType taxType);

    List<ProductTax> findAllByProductId(Integer productId);

    void deleteAllByProductId(Integer productId);

}
