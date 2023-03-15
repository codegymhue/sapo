package vn.sapo.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactSupplierRepository extends JpaRepository<Supplier, Integer> {
//    @Query(value = "", nativeQuery = true)
//    Optional<Supplier> findByIdAndContactId(Integer supplierId, Long contactId);
}