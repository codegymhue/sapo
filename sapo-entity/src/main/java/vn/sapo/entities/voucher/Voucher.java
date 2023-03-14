package vn.sapo.entities.voucher;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.TypeDef;
import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.PaymentMethod;
import vn.sapo.entities.voucher_group.VoucherGroup;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "voucher")
@TypeDef(
        typeClass = JsonType.class,
        defaultForType = VoucherObject.class
)
@TypeDef(
        typeClass = JsonType.class,
        defaultForType = VoucherDocument.class
)
@TypeDef(
        typeClass = JsonType.class,
        defaultForType = VoucherSource.class
)
public class Voucher extends BaseEntity {
    private static final long serialVersionUID = -5655785640275386172L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "voucher_code", unique = true, updatable = false)
    private String voucherCode;

    @Column(name = "voucher_object", columnDefinition = "JSON", nullable = false, updatable = false)
    private VoucherObject object;
    @Column(name = "voucher_document", columnDefinition = "JSON", updatable = false)
    private VoucherDocument document;
    @Column(name = "voucher_source", columnDefinition = "JSON", updatable = false)
    private VoucherSource source;

    @Column(name = "reference")
    private Integer reference;

    @Column(name = "amount", nullable = false, updatable = false)
    private BigDecimal amount;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "type", nullable = false, updatable = false)
    private String type;

    @Column(name = "note")
    private Integer note;

    @Column(name = "account_id", nullable = false, updatable = false)
    private Integer accountId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voucher_group_id", updatable = false, foreignKey = @ForeignKey(name = "fk_voucher_voucher_group"))
    private VoucherGroup group;

    @Column(name = "voucher_group_id", insertable = false, updatable = false)
    private Integer groupId;

    @Setter(AccessLevel.NONE)
    @Column(name = "issued_at", nullable = false, columnDefinition = TIMESTAMP_DEFAULT_CURRENT_TIMESTAMP)
    private Instant issuedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", foreignKey = @ForeignKey(name = "fk_voucher_payment_method"))
    private PaymentMethod paymentMethod;

    @Column(name = "payment_method_id", insertable = false, updatable = false)
    private String paymentMethodId;

    @Override
    public void prePersist() {
        super.prePersist();
        issuedAt = Instant.now();
    }

    public Voucher setGroupId(Integer groupId) {
        this.group = new VoucherGroup(this.groupId = groupId);
        return this;
    }

    public Voucher setPaymentMethodId(String paymentMethodId) {
        this.paymentMethod = new PaymentMethod(this.paymentMethodId = paymentMethodId);
        return this;
    }
}
