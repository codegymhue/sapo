package vn.sapo.entities.rbac;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
@Setter
@Getter
@Accessors(chain = true)
@Embeddable
public class RbacTenantRolePermissionId implements Serializable {
    private static final long serialVersionUID = 4848529601605739094L;
    @NotNull
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @NotNull
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RbacTenantRolePermissionId that = (RbacTenantRolePermissionId) o;
        return Objects.equals(this.permissionId, that.permissionId) &&
                Objects.equals(this.roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, roleId);
    }

}