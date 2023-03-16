package vn.sapo.supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierStatus;
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

            if (filter.getFilter() != null) {
                Predicate predicateSupplierCode = criteriaBuilder.like(root.get("supplierCode"), '%' + filter.getFilter() + '%');
                Predicate predicatePhone = criteriaBuilder.like(root.get("phone"), '%' + filter.getFilter() + '%');
                Predicate predicateName = criteriaBuilder.like(root.get("fullName"), '%' + filter.getFilter() + '%');
                Predicate predicateKw = criteriaBuilder.or(predicateSupplierCode, predicatePhone, predicateName);
                predicates.add(predicateKw);
            }
            if (!filter.getGroupIds().isEmpty()) {
                Predicate predicate = criteriaBuilder.or(root.get("group").get("id").in(filter.getGroupIds()));
                predicates.add(predicate);
            }

            if (!filter.getEmployeeIds().isEmpty()) {
                Predicate predicate = criteriaBuilder.or(root.get("employee").get("id").in(filter.getEmployeeIds()));
                predicates.add(predicate);
            }

            List<String> tagsToSearch = filter.getTags();
            Predicate tagsPredicate = null;

            if (!tagsToSearch.isEmpty()) {
                Expression<Boolean> fistTag = criteriaBuilder
                        .function("JSON_CONTAINS",
                                Boolean.class,
                                root.get("tags"),
                                criteriaBuilder.literal('"' + tagsToSearch.get(0) + '"')
                        );
                tagsPredicate = criteriaBuilder.isTrue(fistTag);

                for (int i = 1; i < tagsToSearch.size(); i++) {
                    String remainingTags = tagsToSearch.get(i);

                    Expression<Boolean> leftoverTags = criteriaBuilder
                            .function("JSON_CONTAINS",
                                    Boolean.class,
                                    root.get("tags"),
                                    criteriaBuilder.literal('"' + remainingTags + '"')
                            );
                    tagsPredicate = criteriaBuilder.or(tagsPredicate, criteriaBuilder.isTrue(leftoverTags));
                }
            }

            if (tagsPredicate != null) {
                predicates.add(tagsPredicate);
            }

            Predicate predicate;
            if (!filter.getStatuses().isEmpty()) {
                predicate = criteriaBuilder.or(root.get("status").in(filter.getStatuses()));
                predicates.add(predicate);
            } else {
                predicate = criteriaBuilder.or(criteriaBuilder.equal(root.get("status"), SupplierStatus.AVAILABLE),
                        criteriaBuilder.equal(root.get("status"), SupplierStatus.UNAVAILABLE)
                );
                predicates.add(predicate);

            }
            Date createdFrom = filter.getCreatedFrom();
            Date createdTo = filter.getCreatedTo();
            Predicate createdAtPredicate = criteriaBuilder.conjunction();
            Path<Instant> createdAtPath = root.get("createdAt");
            if (createdFrom != null && createdTo != null) {
                createdAtPredicate = criteriaBuilder.between(createdAtPath, createdFrom.toInstant(), createdTo.toInstant());

            } else {
                if (createdFrom != null) {
                    createdAtPredicate = criteriaBuilder.greaterThan(createdAtPath, createdFrom.toInstant());

                }
                if (createdTo != null) {
                    createdAtPredicate = criteriaBuilder.lessThan(createdAtPath, createdTo.toInstant());
                }
            }
            predicates.add(createdAtPredicate);

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);

    }

}
