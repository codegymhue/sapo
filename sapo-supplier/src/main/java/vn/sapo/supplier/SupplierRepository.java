package vn.sapo.supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.product.ProductStatus;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.entities.supplier.SupplierStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findAllByStatusNot(SupplierStatus status);

    Page<Supplier> findAll(Pageable pageable);

    @Query("SELECT s FROM Supplier AS s WHERE s.status = :status")
    Page<Supplier> findAllByStatus(@Param("status") SupplierStatus status, Pageable pageable);

    @Query("SELECT tags FROM Supplier")
    List<String> findTags();

//    @Query(value = "", nativeQuery = true)
//    Optional<Supplier> findByIdAndContactId(Integer supplierId);
}