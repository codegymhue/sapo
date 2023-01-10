package vn.sapo.customerGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGroup;

import java.util.List;
import java.util.Optional;
@Repository
public interface CustomerGroupRepository  extends JpaRepository<CustomerGroup, Integer> {

    @Query("select new vn.sapo.customerGroup.dto.CustomerGroupResult(" +
            "g.id," +
            "g.title," +
            "g.cusGrpCode," +
            "g.pricingPolicyId," +
            "g.paymentMethodId," +
            "g.createdAt, " +
            "count(c.groupId)," +
            "g.description,g.discount)" +
            "from CustomerGroup as g " +
            "left join Customer as c " +
            "on g.id = c.groupId " +
            "group by g.id")
    List<CustomerGroupResult> sortByGroup();
}




