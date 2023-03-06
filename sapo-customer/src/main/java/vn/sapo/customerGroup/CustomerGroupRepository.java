package vn.sapo.customerGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.customerGroup.dto.ICustomerGroup;
import vn.sapo.entities.customer.CustomerGroup;

import java.util.List;
@Repository
public interface CustomerGroupRepository  extends JpaRepository<CustomerGroup, Integer> {
<<<<<<< HEAD
    @Query("SELECT " +
            "g.id AS id," +
            "g.title AS title," +
            "g.cusGrpCode AS cusGrpCode," +
            "g.createdAt AS createdAt, " +
            "count(c.groupId) AS countCus," +
            "g.description AS description " +
            "FROM CustomerGroup AS g " +
            "LEFT JOIN Customer AS c " +
            "ON g.id = c.groupId " +
            "GROUP BY g.id")
    List<ICustomerGroupResult> sortByGroup();
=======
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
>>>>>>> cf9e56958713b8ee99a298cabdf1ddcf9488ab60
}




