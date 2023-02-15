package vn.sapo.customerGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.customerGroup.dto.ICustomerGroup;
import vn.sapo.entities.customer.CustomerGroup;

import java.util.List;
@Repository
public interface CustomerGroupRepository  extends JpaRepository<CustomerGroup, Integer> {
    @Query("select " +
            "g.id as id," +
            "g.title as title," +
            "g.cusGrpCode as cusGrpCode," +
            "g.createdAt as createdAt, " +
            "count(c.groupId) as countCus," +
            "g.description as description " +
            "from CustomerGroup as g " +
            "left join Customer as c " +
            "on g.id = c.groupId " +
            "group by g.id")
    List<ICustomerGroup> sortByGroup();
}




