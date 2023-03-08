package vn.sapo.voucher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.sapo.entities.voucher_group.VoucherGroup;
import vn.sapo.entities.voucher_group.VoucherGroupStatus;
import vn.sapo.entities.voucher_group.VoucherGroupType;
import vn.sapo.voucher.voucher_group.VoucherGroupRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class VoucherGroupRepositoryIntegrationTest {
    @Autowired
    VoucherGroupRepository voucherGroupRepository;
    List<VoucherGroup> groups;

    @BeforeEach
    public void setUp() {
        groups = new ArrayList<>() {{
            add((new VoucherGroup()
                    .setVouGroupCode("THANHTOAN")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Thanh toán cho đơn hàng")));
            add((new VoucherGroup()
                    .setVouGroupCode("DATCOC")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Đối tác vận chuyển đặt cọc")));
            add((new VoucherGroup()
                    .setVouGroupCode("THUNO")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Thu nợ đối tác vận chuyển")));
            add((new VoucherGroup()
                    .setVouGroupCode("RVGN7")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Thu nhập khác")));
            add((new VoucherGroup()
                    .setVouGroupCode("RVGN6")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Tiền thưởng")));
            add((new VoucherGroup()
                    .setVouGroupCode("RVGN5")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Tiền bồi thường")));
            add((new VoucherGroup()
                    .setVouGroupCode("RVGN4")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Cho thuê tài sản")));
            add((new VoucherGroup()
                    .setVouGroupCode("RVGN3")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Nhượng bán, thanh lý tài sản")));
            add((new VoucherGroup()
                    .setVouGroupCode("RVGN2")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Thu nợ khách hàng")));
            add((new VoucherGroup()
                    .setVouGroupCode("TUDONG")
                    .setType(VoucherGroupType.RECEIPT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Tự động")));


            add((new VoucherGroup()
                    .setVouGroupCode("THANHTOANDONNHAP")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Thanh toán cho đơn nhập hàng")));
            add((new VoucherGroup()
                    .setVouGroupCode("TRANO")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Trả nợ đối tác vận chuyển")));
            add((new VoucherGroup()
                    .setVouGroupCode("PVGN15")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Chi phí khác")));
            add((new VoucherGroup()
                    .setVouGroupCode("PVGN14")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Chi phí sản xuất")));
            add((new VoucherGroup()
                    .setVouGroupCode("PVGN13")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Chi phí nguyên - vật liệu")));
            add((new VoucherGroup()
                    .setVouGroupCode("PVGN12")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Chi phí sinh hoạt")));
            add((new VoucherGroup()
                    .setVouGroupCode("PVGN11")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Chi phí nhân công")));
            add((new VoucherGroup()
                    .setVouGroupCode("PVGN10")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Chi phí bán hàng")));
            add((new VoucherGroup()
                    .setVouGroupCode("PVGN9")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Chi phí quản lý cửa hàng")));
            add((new VoucherGroup()
                    .setVouGroupCode("TUDONG")
                    .setType(VoucherGroupType.PAYMENT_VOUCHER)
                    .setStatus(VoucherGroupStatus.ACTIVE)
                    .setTitle("Tự động")));
        }};
    }

    @Test
    public void create() {
        voucherGroupRepository.saveAll(groups);
    }
}
