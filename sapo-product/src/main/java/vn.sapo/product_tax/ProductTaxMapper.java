package vn.sapo.product_tax;

import org.springframework.stereotype.Component;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.tax.ProductTax;
import vn.sapo.product_tax.dto.ProductTaxParam;
import vn.sapo.product_tax.dto.ProductTaxResult;

@Component
public class ProductTaxMapper {

    public ProductTaxResult toDTO(ProductTax productTax) {
        return new ProductTaxResult()
                .setId(productTax.getId())
                .setProductId(productTax.getProductId())
                .setTaxId(productTax.getTaxId())
                .setTaxType(productTax.getTaxType());
    }

    public ProductTax toModel(ProductTaxParam productTaxParam, Product product) {
        return new ProductTax()
                .setTaxId(productTaxParam.getTaxId())
                .setTaxType(productTaxParam.getTaxType())
                .setProductId(product.getId());
    }
}
