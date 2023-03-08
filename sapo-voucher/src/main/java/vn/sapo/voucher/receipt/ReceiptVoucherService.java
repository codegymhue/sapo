package vn.sapo.voucher.receipt;


import vn.sapo.voucher.shared.dto.CreateVoucherParam;
import vn.sapo.voucher.shared.dto.VoucherResult;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptVoucherService {

    List<VoucherResult> findAllByOrderId(Integer orderId);

    VoucherResult create(CreateVoucherParam createVoucherParam);

    BigDecimal getPaidTotalByCustomerIds(List<Integer> customerIds);

    BigDecimal getDebtTotalByCustomerIds(List<Integer> customerIds);

}
