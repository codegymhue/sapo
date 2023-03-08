package vn.sapo.voucher.voucher_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.voucher_group.VoucherGroup;
@Repository
public interface VoucherGroupRepository extends JpaRepository<VoucherGroup, Integer> {
}