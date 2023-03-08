package vn.sapo.voucher.voucher_group;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.voucher_group.VoucherGroupStatus;

import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
public class BaseVoucherGroup {
    private String vouGroupCode;
    private String title;
    private VoucherGroupStatus status;
    private boolean counted;
    private String note;
    private Instant createdAt;
    private Instant updatedAt;
}
