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
@Table(name = "rbac_operation_permission")
public class RbacOperationPermission extends BaseEntity {
    private static final long serialVersionUID = -2881538767006097373L;
    @EmbeddedId
    private RbacOperationPermissionId id;

    @MapsId("permissionId")
    @ManyToOne( optional = false)
    @JoinColumn(name = "permission_id", nullable = false, foreignKey = @ForeignKey(name = "fk_op_permission"))
    private RbacPermission permission;

    @MapsId("operationId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "operation_id", nullable = false, foreignKey = @ForeignKey(name = "fk_op_operation"))
    private RbacOperation operation;

    public RbacOperationPermission(Integer operationId, Integer permissionId) {
        id = new RbacOperationPermissionId(operationId, permissionId);
    }

}
