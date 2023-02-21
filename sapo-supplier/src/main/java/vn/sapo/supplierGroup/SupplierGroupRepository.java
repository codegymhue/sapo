package vn.sapo.supplierGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.sapo.entities.supplier.SupplierGroup;

import java.util.List;

public interface SupplierGroupRepository extends JpaRepository<SupplierGroup, Integer> {
    boolean existsBySupGroupCode(String supGroupCode);

    boolean existsByTitle(String title);
}
