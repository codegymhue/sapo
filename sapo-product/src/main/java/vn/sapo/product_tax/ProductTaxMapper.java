package vn.sapo.product_tax;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.tax.ProductTax;
import vn.sapo.product_tax.dto.ProductTaxParam;
import vn.sapo.product_tax.dto.ProductTaxResult;

@Component
public class ProductTaxMapper {
    @Autowired
    private ModelMapper modelMapper;
    public ProductTaxResult toDTO(ProductTax productTax) {
        return modelMapper.map(productTax, ProductTaxResult.class);
    }
}
