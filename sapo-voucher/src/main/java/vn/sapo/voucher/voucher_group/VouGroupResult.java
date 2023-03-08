package vn.sapo.voucher.voucher_group;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class VouGroupResult extends BaseVoucherGroup {
    private Integer id;
}
