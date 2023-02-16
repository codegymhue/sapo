package vn.sapo.entities.rbac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Embeddable
public class RbacModulePermissionId implements Serializable {
    private static final long serialVersionUID = -2707927982265171777L;
    @NotNull
    @Column(name = "module_id", nullable = false)
    private Integer moduleId;
    @NotNull
    @Column(name = "permission_id", nullable = false)
    private Integer permissionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RbacModulePermissionId that = (RbacModulePermissionId) o;
        return Objects.equals(this.permissionId, that.permissionId) &&
                Objects.equals(this.moduleId, that.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, moduleId);
    }

}