package vn.sapo.customerGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.CustomerGroup;

import java.util.List;
import java.util.Optional;

public interface CustomerGroupRepository  extends JpaRepository<CustomerGroup, Integer> {
    @Query("select new vn.sapo.customerGroup.dto.CustomerGroupResult(g.id,g.cusGrpCode," +
            "g.title," +
            "g.createdAt, count(c.groupId))" +
            "from CustomerGroup as g " +
            "left join Customer as c " +
            "on g.id = c.groupId " +
            "group by g.id")
    List<CustomerGroupResult> sortByGroup();

    @Query("select g.pricing_policy_id,g.cus_grp_code,g.title from CustomerGroup as g" +
            "right join pricing_policy as p" +
            "on g.pricing_policy_id = p.id")
    List<CustomerGroupResult> getAllPricingPolicy();

    @Query("select c.payment_method_id,p.id,p.title from CustomerGroup as c" +
            "right join PaymentMethod as p" +
            "on c.pricing_policy_id = p.id")
    List<CustomerGroupResult> getAllPaymentMethod();
}