package vn.sapo.product_tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.tax.ProductTax;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.product_tax.dto.ProductTaxParam;
import vn.sapo.product_tax.dto.ProductTaxResult;
import vn.sapo.tax.TaxService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTaxServiceImpl implements ProductTaxService {
    @Autowired
    ProductTaxRepository productTaxRepository;
    @Autowired
    TaxService taxService;
    @Autowired
    ProductTaxMapper productTaxMapper;

    @Override
    @Transactional
    public List<ProductTaxResult> createAll(List<ProductTaxParam> productTaxParams, Product product) {
        int productId = product.getId();
        List<ProductTax> entities = new ArrayList<>();
        if (product.isApplyTax()) {
            entities = productTaxParams.stream()
                    .map(param -> {
                        if (param.getTaxId() == null) {
                            param.setTaxId(1);
                        }
                        if (!taxService.existsById(param.getTaxId()))
                            throw new NotFoundException("Tax not found");
                        return new ProductTax(productId, param.getTaxId(), param.getTaxType());
                    })
                    .collect(Collectors.toList());

        } else {
            entities = productTaxParams.stream()
                    .map(param -> {
                        if (param.getTaxId() == null) {
                            param.setTaxId(1);
                        }
                        return new ProductTax(productId, param.getTaxId(), param.getTaxType());
                    })
                    .collect(Collectors.toList());
        }
        return productTaxRepository.saveAll(entities).stream()
                .map(productTaxMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductTaxResult> findAllByProductId(Integer productId) {
        return productTaxRepository.findAllByProductId(productId)
                .stream().map(productTaxMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByProductId(Integer productId) {
        productTaxRepository.deleteAllByProductId(productId);
    }
}
