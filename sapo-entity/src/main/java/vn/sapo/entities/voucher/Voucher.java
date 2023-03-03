package vn.sapo.entities.voucher;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.BaseEntity;
import vn.sapo.entities.PaymentMethod;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "voucher")
public class Voucher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "voucher_code", unique = true)
    private String voucherCode;

    @Column(name = "object_id", nullable = false)
    private Integer objectId;

    @Column(name = "object_type", nullable = false)
    private Integer objectType;

    @Column(name = "reference")
    private Integer reference;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "note")
    private Integer note;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", foreignKey = @ForeignKey(name = "fk_voucher_payment_method"))
    private PaymentMethod paymentMethod;

    @Column(name = "payment_method_id", insertable = false, updatable = false)
    private String paymentMethodId;


    public Voucher(Integer id) {
        this.id = id;
    }

    public Voucher setPaymentMethodId(String paymentMethodId) {
        this.paymentMethod = new PaymentMethod(this.paymentMethodId = paymentMethodId);
        return this;
    }


}
