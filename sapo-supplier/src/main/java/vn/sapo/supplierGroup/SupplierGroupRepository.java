package vn.sapo.supplierGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.sapo.entities.supplier.SupplierGroup;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SupplierGroupRepository extends JpaRepository<SupplierGroup, Integer> {
    boolean existsBySupGroupCode(String supGroupCode);

    boolean existsByTitle(String title);

    List<SupplierGroup> findBySupGroupCodeIn(Set<String> groupCodes);
}
