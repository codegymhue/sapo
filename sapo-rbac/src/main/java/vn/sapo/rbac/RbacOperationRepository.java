package vn.sapo.rbac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.rbac.RbacModule;
import vn.sapo.entities.rbac.RbacOperation;

@Repository
public interface RbacOperationRepository extends JpaRepository<RbacOperation, Integer> {
}