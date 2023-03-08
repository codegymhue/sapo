package vn.sapo.voucher.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.voucher.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}
