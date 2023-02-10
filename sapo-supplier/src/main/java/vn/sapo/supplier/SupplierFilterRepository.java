package vn.sapo.supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.supplier.dto.SupplierFilter;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface SupplierFilterRepository extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor<Supplier> {

    default Page<Supplier> findAllByFilters(SupplierFilter filter, Pageable pageable) {
        return findAll((root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if(filter.getFilter() != null){
                Predicate predicateSupplierCode = criteriaBuilder.like(root.get("supplierCode"),'%' + filter.getFilter() + '%');
                Predicate predicatePhone = criteriaBuilder.like(root.get("phone"),'%' + filter.getFilter() + '%');
                Predicate predicateName = criteriaBuilder.like(root.get("name"),'%' + filter.getFilter() + '%');
                Predicate predicateKw = criteriaBuilder.or(predicateSupplierCode, predicatePhone,predicateName);
                predicates.add(predicateKw);
            }
            if (!filter.getSupplierGroupId().isEmpty()) {
                Predicate predicate = criteriaBuilder.or(root.get("group").get("id").in(filter.getSupplierGroupId()));
                predicates.add(predicate);
            }

            if (!filter.getEmployeeIds().isEmpty()){
                Predicate predicate = criteriaBuilder.or(root.get("employee").get("id").in(filter.getEmployeeIds()));
                predicates.add(predicate);
            }

            if(!filter.getStatuses().isEmpty()){
                Predicate predicate = criteriaBuilder.or(root.get("status").in(filter.getStatuses()));
                predicates.add(predicate);
            }
            Date createdFrom = filter.getCreatedFrom();
            Date createdTo = filter.getCreatedTo();
            Predicate createdAtPredicate = criteriaBuilder.conjunction();
            Path<Date> createdAtPath = root.get("createdAt");
            if (createdFrom != null && createdTo != null)
                createdAtPredicate = criteriaBuilder.between(createdAtPath, createdFrom, createdTo);
            else {
                if (createdFrom != null)
                    createdAtPredicate = criteriaBuilder.greaterThan(createdAtPath, createdFrom);

                if (createdTo != null)
                    createdAtPredicate = criteriaBuilder.lessThan(createdAtPath, createdTo);
            }
            predicates.add(createdAtPredicate);



            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);

    }
}
