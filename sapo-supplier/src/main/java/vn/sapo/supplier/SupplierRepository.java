package vn.sapo.supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.supplier.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Page<Supplier> findAll(Pageable pageable);
}