package vn.sapo.rbac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.rbac.RbacPermission;

@Repository
public interface RbacPermissionRepository extends JpaRepository<RbacPermission, Integer> {
}