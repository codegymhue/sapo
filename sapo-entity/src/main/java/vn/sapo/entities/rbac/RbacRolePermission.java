package vn.sapo.entities.rbac;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "rbac_role_permission")
public class RbacRolePermission extends BaseEntity {
    private static final long serialVersionUID = 7127370201089803171L;
    @EmbeddedId
    private RbacRolePermissionId id;

    @MapsId("roleId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rp_role"))
    private RbacRole role;

    @MapsId("permissionId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "permission_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rp_permission"))
    private RbacPermission permission;

    public RbacRolePermission(Integer roleId, Integer permissionId) {
        id = new RbacRolePermissionId(roleId, permissionId);
    }


}