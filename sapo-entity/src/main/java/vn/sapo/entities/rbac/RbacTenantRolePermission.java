package vn.sapo.entities.rbac;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "rbac_tenant_role_permission")
public class RbacTenantRolePermission {
    @EmbeddedId
    private RbacTenantRolePermissionId id;

    @MapsId("permissionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false)
    private RbacTenantPermission permission;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public RbacTenantRolePermissionId getId() {
        return id;
    }

    public void setId(RbacTenantRolePermissionId id) {
        this.id = id;
    }

    public RbacTenantPermission getPermission() {
        return permission;
    }

    public void setPermission(RbacTenantPermission permission) {
        this.permission = permission;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}