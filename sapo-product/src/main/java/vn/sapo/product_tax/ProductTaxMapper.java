package vn.sapo.product_tax;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.tax.ProductTax;
import vn.sapo.entities.tax.Tax;
import vn.sapo.product_tax.dto.ProductTaxParam;
import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.tax.dto.CreateTaxParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductTaxMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ProductTaxResult toDTO(ProductTax productTax) {
        return modelMapper.map(productTax, ProductTaxResult.class);
    }

//    public ProductTax toModel(ProductTaxParam productTax) {
//       // return modelMapper.map(productTax, ProductTaxResult.class);
//    }
//
//    public List<Tax> toModelList(List<CreateTaxParam> taxListParam) {
//        return taxListParam.stream().map(this::toModel).collect(Collectors.toList());
//    }
}
