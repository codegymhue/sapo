package vn.sapo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.brand.BrandMapper;
import vn.sapo.category.CategoryMapper;
import vn.sapo.entities.product.Product;
import vn.sapo.media.MediaMapper;
import vn.sapo.product.dto.*;
import vn.sapo.tax.dto.TaxMapper;

import java.math.BigDecimal;

@Component
public class ProductMapper {


    @Autowired
    BrandMapper brandMapper;

    @Autowired
    TaxMapper taxMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    MediaMapper mediaMapper;


    public Product toModel(ProductParam productParam) {
        return new Product(productParam.getCategoryId(), productParam.getBrandId())
                .setTitle(productParam.getTitle())
                .setStatus(productParam.getStatus())
                .setDescription(productParam.getDescription())
                .setUnit(productParam.getUnit())
                .setSku(productParam.getSku())
                .setImportPrice(productParam.getImportPrice())
                .setRetailPrice(productParam.getRetailPrice())
                .setWholesalePrice(productParam.getWholesalePrice())
                .setBrandId(productParam.getBrandId())
                .setCategoryId(productParam.getCategoryId());
    }

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
        return new ProductResult()
                .setId(product.getId())
                .setTitle(product.getTitle())
                .setStatus(product.getStatus())
                .setSku(product.getSku())
                .setBrandId(product.getBrandId())
                .setCategoryId(product.getCategoryId())
                .setCategory(categoryMapper.toDTO(product.getCategory()))
                .setBrandId(product.getBrandId())
                .setBrand(brandMapper.toDTO(product.getBrand()))
                .setDescription(product.getDescription())
                .setUnit(product.getUnit())
                .setImportPrice(product.getImportPrice())
                .setWholesalePrice(product.getWholesalePrice())
                .setRetailPrice(product.getRetailPrice())
                .setApplyTax(product.getApplyTax())
                .setCategory(categoryMapper.toDTO(product.getCategory()))
                .setBrand(brandMapper.toDTO(product.getBrand()));
//        List<TaxResult> taxSaleList = product.getTaxSale().stream().map(taxMapper::toDTO).collect(Collectors.toList());
//        result.setTaxSaleList(taxSaleList);
//        List<TaxResult> taxPurchaseList = product.getTaxPurchase().stream().map(taxMapper::toDTO).collect(Collectors.toList());
//        result.setTaxPurchaseList(taxPurchaseList);
//        result.setBrand(brandMapper.toDTO(product.getBrand()))
//                .setCategory(categoryMapper.toDTO(product.getCategory()));
//        return result;
    }

    public ProductDetailResult toDTODetail(Product product) {
        return new ProductDetailResult()
                .setId(product.getId())
                .setTitle(product.getTitle())
                .setStatus(product.getStatus())
                .setSku(product.getSku())
                .setDescription(product.getDescription())
                .setUnit(product.getUnit())
                .setCreateAt(product.getCreatedAt())
                .setUpdateAt(product.getUpdatedAt())
                .setImportPrice(product.getImportPrice())
                .setWholesalePrice(product.getWholesalePrice())
                .setRetailPrice(product.getRetailPrice())
                .setApplyTax(product.getApplyTax())
                .setIsTaxInclusive(product.getIsTaxInclusive());
    }

    public ProductItemResult toDTOPage(Product product) {
        return new ProductItemResult()
                .setId(product.getId())
                .setTitle(product.getTitle())
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
