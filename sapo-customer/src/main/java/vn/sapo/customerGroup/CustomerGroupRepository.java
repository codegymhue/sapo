package vn.sapo.customerGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.customerGroup.dto.ICustomerGroupResult;
import vn.sapo.entities.customer.CustomerGroup;

@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Integer> {
    @Query("select " +
            "g.id as id," +
            "g.title as title," +
            "g.type as type," +
            "g.cusGrpCode as cusGrpCode," +
            "g.createdAt as createdAt, " +
            "count(c.groupId) as countCus," +
            "g.note as note " +
            "from CustomerGroup as g " +
            "left join Customer as c " +
            "on g.id = c.groupId " +
            "group by g.id")
    Page<ICustomerGroupResult> findAllCustomerGroupPageable(Pageable pageable);

    //    @Query("SELECT " +
//            "g.id AS id," +
//            "g.cusGrpCode AS cusGrpCode," +
//            "g.title AS title," +
//            "g.type AS type," +
//            "g.note AS note " +
//            "FROM CustomerGroup AS g " +
//            "WHERE g.id = :id"
//    )
    CustomerGroup findCustomerGroupById (Integer id);

    boolean existsByCusGrpCode(String code);

    boolean existsByTitle(String title);
}




