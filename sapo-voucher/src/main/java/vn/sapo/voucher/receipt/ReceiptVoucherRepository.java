package vn.sapo.voucher.receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.voucher.Voucher;

@Repository
public interface ReceiptVoucherRepository extends JpaRepository<Voucher, Integer> {
}
