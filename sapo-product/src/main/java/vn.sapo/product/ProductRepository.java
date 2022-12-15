package vn.sapo.product;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.product.ProductStatus;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAllByTitleContaining(String title, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.categoryId = :categoryId AND p.title LIKE %:title%")
    Page<Product> findAllByTitleContainingAndCategoryId(@Param("categoryId") Integer categoryId, @Param("title") String title, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.brandId = :brandId AND p.title LIKE %:title%")
    Page<Product> findAllByTitleContainingAndBrandId(@Param("brandId") Integer brandId, @Param("title") String title, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.status = :status AND p.title LIKE %:title%")
    Page<Product> findAllByTitleContainingAndStatus(@Param("status") ProductStatus status, @Param("title") String title, Pageable pageable);

}