package vn.sapo.entities.rbac;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "rbac_module")
public class RbacModule extends BaseEntity {
    private static final long serialVersionUID = 7963198930656959401L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 75)
    @NotNull
    @Column(name = "module_code", unique = true, nullable = false, length = 75)
    private String moduleCode;

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

    @OneToMany(mappedBy = "module")
    private Set<RbacModulePermission> modulePermissions = new LinkedHashSet<>();
}
