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
@Table(name = "rbac_module_permission")
public class RbacModulePermission extends BaseEntity {
    private static final long serialVersionUID = -5058445088824414872L;
    @EmbeddedId
    private RbacModulePermissionId id;

    @MapsId("moduleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "module_id", nullable = false, foreignKey = @ForeignKey(name = "fk_mp_module"))
    private RbacModule module;

    @MapsId("permissionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false, foreignKey = @ForeignKey(name = "fk_mp_permission"))
    private RbacPermission permission;


    public RbacModulePermission(Integer moduleId, Integer permissionId) {
        id = new RbacModulePermissionId(moduleId, permissionId);
    }

}
