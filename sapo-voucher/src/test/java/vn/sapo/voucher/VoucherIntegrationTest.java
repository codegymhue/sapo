package vn.sapo.voucher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.sapo.entities.voucher.Voucher;
import vn.sapo.voucher.shared.VoucherRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class VoucherIntegrationTest {
    @Autowired
    VoucherRepository voucherRepository;
    List<Voucher> vouchers;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void create() {
        voucherRepository.saveAll(vouchers);
    }


}
