package vn.sapo.customerGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.customerGroup.dto.ICustomerGroup;
import vn.sapo.entities.customer.CustomerGroup;

import java.util.List;

@Repository
public interface CustomerGroupRepository  extends JpaRepository<CustomerGroup, Integer> {

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
    List<ICustomerGroup> sortByGroup();

    @Query(value = "SELECT cus_grp_code AS cgc " +
            "FROM customer_group AS cg " +
            "WHERE cg.cus_grp_code " +
            "LIKE 'CTN%' " +
            "ORDER BY cgc " +
            "DESC LIMIT 1;",
            nativeQuery = true)
    String getMaxSystemCustomerGroupCode();

    boolean existsByCusGrpCode(String code);
}




