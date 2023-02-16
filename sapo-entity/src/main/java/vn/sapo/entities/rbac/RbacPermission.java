package vn.sapo.entities.rbac;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "rbac_permission")
public class RbacPermission extends BaseEntity {
    private static final long serialVersionUID = -4215962191690812169L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 75)
    @NotNull
    @Column(name = "permission_code", nullable = false, length = 75)
    private String permissionCode;

    @Size(max = 75)
    @NotNull
    @Column(name = "title", nullable = false, length = 75)
    private String title;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Lob
    @Column(name = "content")
    private String content;
}