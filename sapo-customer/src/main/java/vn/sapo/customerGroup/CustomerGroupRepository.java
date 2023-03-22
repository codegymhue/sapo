package vn.sapo.customerGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.customerGroup.dto.ICustomerGroupResult;
import vn.sapo.entities.customer.CustomerGroup;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Integer>, JpaSpecificationExecutor<CustomerGroup> {

    @Query(value = "SELECT " +
            "g.id, " +
            "g.title, " +
            "g.cus_grp_code as cusGrpCode, " +
            "g.type , " +
            "(SELECT COUNT(customer_group_id) " +
            "FROM customer " +
            "WHERE customer_group_id = g.id) AS countCus, " +
            "g.note, " +
            "g.created_at AS createdAt " +
            "FROM customer_group AS g;"
            ,
            nativeQuery = true)
    Page<ICustomerGroupResult> test(Pageable pageable);

        default Page<CustomerGroup> findAllCustomerGroupPageable(Pageable pageable) {

        return findAll(
                (root, criteriaQuery, criteriaBuilder) -> {

                    List<Predicate> predicates = new ArrayList<>();

                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }
                , pageable);
    }

//    @Query("select " +
//            "g.id as id," +
//            "g.title as title," +
//            "g.type as type," +
//            "g.cusGrpCode as cusGrpCode," +
//            "g.createdAt as createdAt, " +
//            "count(c.groupId) as countCus," +
//            "g.note as note " +
//            "from CustomerGroup as g " +
//            "left join Customer as c " +
//            "on g.id = c.groupId " +
//            "group by g.id")
//    List<ICustomerGroupResult> sortByGroup();

    boolean existsByCusGrpCode(String code);

    boolean existsByTitle(String title);

//    @Query(value = "SELECT " +
//            "g.id, " +
//            "g.title, " +
//            "g.cus_grp_code as cusGrpCode, " +
//            "g.type , " +
//            "(SELECT COUNT(customer_group_id) " +
//            "FROM customer " +
//            "WHERE customer_group_id = g.id) AS countCus, " +
//            "g.note, " +
//            "g.created_at AS createdAt " +
//            "FROM customer_group AS g;"
//            ,
//            nativeQuery = true)
//    List<ICustomerGroupResult> findAllCustomerGroupResult();
}




