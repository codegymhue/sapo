package vn.sapo.customerGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.sapo.customerGroup.dto.CustomerGroupFilter;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.CustomerGroup;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface CustomerGroupFilterRepository
        extends JpaRepository<CustomerGroup, Integer>, JpaSpecificationExecutor<CustomerGroup> {

//    default Page<CustomerGroup> findAllByFilters(CustomerGroupFilter filter, Pageable pageable) {
//        return findAll((root, criteriaQuery, criteriaBuilder) -> {
//
//            List<Predicate> predicates = new ArrayList<>();
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        }, pageable);
//    }
}
