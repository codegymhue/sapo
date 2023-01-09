package vn.sapo.product;

import vn.sapo.entities.product.Product;
import vn.sapo.product.dto.*;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductResult> findAll();

    ProductResult findById(Integer id);

    ProductDetailResult findDetailById(Integer id);

    List<ProductResult> findAllProductByDeleted(boolean deleted);

    ProductResult findByIdProduct(Integer id);

    Product createProduct(ProductCreate productCreate);

    ProductResult create(CreateProductParam createProductParam);

    void update(UpdateProductParam updateProductParam);

    ProductResult createShortProduct(ProductShortParam productShortParam);

    Map<String, Object> getAllProductItemPage(Integer pageNo, Integer pageSize, String title,
                                              Integer categoryId, Integer brandId, String status,
                                              String typeSort, String nameFieldSort);

    Map<String, Object> getAllProductVariantPage(Integer pageNo, Integer pageSize, String title,
                                                 Integer categoryId, Integer brandId, String status,
                                                 String typeSort, String nameFieldSort);

    void changeStatusToAvailable(List<Integer> productIds,boolean status);

    void deleteSoftProduct(List<Integer> productIds);

    void saveChangeApplyTax(Integer applyTax, List<Integer> productIds);

    List<ProductVariantsResult> getAllCheckInventoryProduct(List<Integer> productIds);

    void deleteProduct(Integer productId);
}
