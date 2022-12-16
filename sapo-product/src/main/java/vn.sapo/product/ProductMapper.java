package vn.sapo.product;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.brand.BrandMapper;
import vn.sapo.category.CategoryMapper;
import vn.sapo.entities.product.Product;
import vn.sapo.media.MediaMapper;
import vn.sapo.product.dto.*;
import vn.sapo.tax.TaxMapper;

import java.math.BigDecimal;

@Component
public class ProductMapper implements InitializingBean {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
//        TypeMap<Product, ProductResult> model2Dto = modelMapper.createTypeMap(Product.class, ProductResult.class);
    }

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    TaxMapper taxMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    MediaMapper mediaMapper;

    public Product toModel(CreateProductParam productWithImageParam) {
        return new Product(productWithImageParam.getCategoryId(), productWithImageParam.getBrandId())
                .setTitle(productWithImageParam.getTitle())
                .setDescription(productWithImageParam.getDescription())
                .setUnit(productWithImageParam.getUnit())
                .setSku(productWithImageParam.getSku())
                .setImportPrice(productWithImageParam.getImportPrice())
                .setRetailPrice(productWithImageParam.getRetailPrice())
                .setWholesalePrice(productWithImageParam.getWholesalePrice())
                .setBrandId(productWithImageParam.getBrandId())
                .setCategoryId(productWithImageParam.getCategoryId());
    }

    public ProductResult toDTO(Product product) {
        return modelMapper.map(product, ProductResult.class);
    }

    public ProductDetailResult toDTODetail(Product product) {
        return new ProductDetailResult()
                .setId(product.getId())
                .setTitle(product.getTitle())
                .setStatus(product.getStatus())
                .setSku(product.getSku())
                .setDescription(product.getDescription())
                .setUnit(product.getUnit())
                .setCreatedAt(product.getCreatedAt())
                .setUpdatedAt(product.getUpdatedAt())
                .setImportPrice(product.getImportPrice())
                .setWholesalePrice(product.getWholesalePrice())
                .setRetailPrice(product.getRetailPrice())
                .setApplyTax(product.getApplyTax())
                .setTaxInclusive(product.getTaxInclusive());
    }

    public ProductItemResult toDTOPage(Product product) {
        return new ProductItemResult()
                .setId(product.getId())
                .setTitle(product.getTitle())
                .setImage("")
                .setStatus(product.getStatus())
                .setAvailable(0)
                .setInventory(0)
                .setCreateAt(product.getCreatedAt())
                .setUpdateAt(product.getUpdatedAt())
                .setCategory(categoryMapper.toDTO(product.getCategory()))
                .setBrand(brandMapper.toDTO(product.getBrand()));
    }
// TODO Nay khong biet o dau
//    public ProductSaleResult toDTOProductSale(Product product) {
//        return new ProductSaleResult()
//                .setId(product.getId())
//                .setTitle(product.getTitle())
//                .setMainUrl(product.getImage())
//                .setSku(product.getSku());
//
//    }


    public Product toModel(ProductShortParam productShortParam) {
        return new Product(Integer.parseInt(productShortParam.getCategoryId()))
                .setId(Integer.parseInt(productShortParam.getId()))
                .setTitle(productShortParam.getTitle())
                .setSku(productShortParam.getSku())
                .setCategoryId(Integer.parseInt(productShortParam.getCategoryId()))
                .setRetailPrice(new BigDecimal(Integer.parseInt(productShortParam.getRetailPrice())))
                .setSku(productShortParam.getSku())
                .setImportPrice(new BigDecimal(Integer.parseInt(productShortParam.getImportPrice())));

    }
}
