package vn.sapo.voucher.receipt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.voucher.shared.VoucherMapper;
import vn.sapo.voucher.shared.dto.CreateVoucherParam;
import vn.sapo.voucher.shared.dto.VoucherResult;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReceiptVoucherServiceImpl implements ReceiptVoucherService {

    @Autowired
    private VoucherMapper voucherMapper;

    @Override
    public List<VoucherResult> findAllByOrderId(Integer orderId) {
        return null;
    }

    @Override
    public VoucherResult create(CreateVoucherParam createVoucherParam) {
        return null;
    }

    @Override
    public BigDecimal getPaidTotalByCustomerIds(List<Integer> customerIds) {
        return null;
    }

    @Override
    public BigDecimal getDebtTotalByCustomerIds(List<Integer> customerIds) {
        return null;
    }
}