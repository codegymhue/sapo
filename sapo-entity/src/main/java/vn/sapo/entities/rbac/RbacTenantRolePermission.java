package vn.sapo.entities.rbac;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "rbac_tenant_role_permission")
public class RbacTenantRolePermission extends BaseEntity {
    @EmbeddedId
    private RbacTenantRolePermissionId id;

    @MapsId("permissionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false, foreignKey = @ForeignKey(name = "fk_trp_permission"))
    private RbacTenantPermission permission;
    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_trp_role"))
    private RbacTenantRole role;

}