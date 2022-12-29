package vn.sapo.product;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.product.ProductStatus;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAllByDeletedIsFalse(Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.title LIKE %:title% AND p.deleted = false ")
    Page<Product> findAllByTitleContaining(@Param("title") String title, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.categoryId = :categoryId AND p.title LIKE %:title% AND p.deleted = false ")
    Page<Product> findAllByTitleContainingAndCategoryId(@Param("categoryId") Integer categoryId, @Param("title") String title, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.brandId = :brandId AND p.title LIKE %:title% AND p.deleted = false")
    Page<Product> findAllByTitleContainingAndBrandId(@Param("brandId") Integer brandId, @Param("title") String title, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.status = :status AND p.title LIKE %:title% AND p.deleted = false")
    Page<Product> findAllByTitleContainingAndStatus(@Param("status") ProductStatus status, @Param("title") String title, Pageable pageable);

    @Query("SELECT p FROM Product AS p WHERE p.title LIKE %:title% AND p.categoryId = :categoryId AND p.brandId = :brandId AND p.deleted = false")
    Page<Product> findAllByTitleContainingAndCategoryIdAndBrandId(@Param("title") String title,@Param("categoryId") Integer categoryId, @Param("brandId") Integer brandId, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.title LIKE %:title% AND p.categoryId = :categoryId AND  p.status = :status AND p.deleted = false")
    Page<Product> findAllByTitleContainingAndCategoryIdAndStatus(@Param("title") String title,@Param("categoryId") Integer categoryId, @Param("status") ProductStatus status, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.title LIKE %:title% AND p.brandId = :brandId AND  p.status = :status AND p.deleted = false")
    Page<Product> findAllByTitleContainingAndBrandIdAndStatus(@Param("title") String title,@Param("brandId") Integer brandId, @Param("status") ProductStatus status, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.title LIKE %:title% AND p.categoryId = :categoryId AND p.brandId = :brandId AND  p.status = :status AND p.deleted = false")
    Page<Product> findAllByTitleContainingAndCategoryIdAndBrandIdAndStatus(@Param("title") String title,@Param("categoryId") Integer categoryId, @Param("brandId") Integer brandId, @Param("status") ProductStatus status, Pageable pageable);

    Optional<Product> findBySku (String sku);
    Optional<Product> findByBarCode (String barCode);

    @Query("SELECT p FROM Product AS p WHERE p.id = :productId AND p.deleted = false")
    Product findByIdByDeletedIsFalse(@Param("productId") Integer id);
}