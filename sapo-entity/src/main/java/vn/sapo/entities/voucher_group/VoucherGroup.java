package vn.sapo.entities.voucher_group;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;

import javax.persistence.*;

/**
 * Loại phiếu thu/chix
 */

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "voucher_group")
public class VoucherGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "voucher_group_code")
    private String vouGroupCode;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VoucherGroupStatus status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VoucherGroupType type;

    @Column(name = "counted", nullable = false)
    private boolean counted;

    @Column(name = "note")
    private String note;

    public VoucherGroup(Integer id) {
        this.id = id;
    }
}
