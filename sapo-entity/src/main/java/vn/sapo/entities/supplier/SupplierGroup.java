package vn.sapo.entities.supplier;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "supplier_group")
@Accessors(chain = true)
public class SupplierGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "sup_grp_code", unique = true, length = 50)
    private String supGroupCode;

    @Column(name = "note")
    private String note;

    public SupplierGroup(Integer id) {
        this.id = id;
    }


}
