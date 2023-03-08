package vn.sapo.supplier;

import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.supplier.dto.SupplierFilter;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import javax.persistence.criteria.*;
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

            //hàm này xài được
//            Predicate inJsonNumbers = criteriaBuilder
//                    .function("JSON_EXTRACT",
//                            String.class,
//                            root.get("tags"),
//                            criteriaBuilder.literal("$[*]")
//                          )
//                    .in(filter.getTags());
//            predicates.add(inJsonNumbers);
//
//            Predicate containsTag = (Predicate) criteriaBuilder
//                    .function(
//                            "JSON_CONTAINS",
//                            Boolean.class,
//                            root.get("tags"),
//                            criteriaBuilder.literal("\"" + filter.getTags() + "\"")
//                    );
//            predicates.add(containsTag);


//            Predicate likeJsonNumbers = criteriaBuilder
//                    .like(
//                            criteriaBuilder.function(
//                                    "JSON_EXTRACT",
//                                    String.class,
//                                    root.get("tags"),
//                                    criteriaBuilder.literal("$[*]")
//                            ),
//                            "%" + filter.getTags() + "%"
//                    );
//            predicates.add(likeJsonNumbers);



//            // Tìm kiếm theo tags
//            if (!filter.getTags().isEmpty()) {
//                Expression<String> jsonExpr = root.get("tags");
//                for (String tag : filter.getTags()) {
//                    Predicate predicate = criteriaBuilder.like(jsonExpr.as(String.class), "%\"" + tag + "\"%");
//                    predicates.add(predicate);
//                }
//            }
//            Predicate tagsPredicate = null;
//            if (!filter.getTags().isEmpty()) {
//                tagsPredicate = criteriaBuilder.or(root.get("tags").in(filter.getTags())) ;
//            }
//
//            if (tagsPredicate != null) {
//                predicates.add(tagsPredicate);
//            }

//            Predicate tagsPredicate = criteriaBuilder.like(
//                    criteriaBuilder.function("JSON_EXTRACT", String.class, root.get("tags"), criteriaBuilder.literal("$.tags")),
//                    "%" + filter.getTags() + "%");
//
//            for (int i = 1; i < tagsToSearch.size(); i++) {
//                String tag = tagsToSearch.get(i);
//                tagsPredicate = builder.or(tagsPredicate, builder.like(
//                        builder.function("JSON_EXTRACT", String.class, root.get("tags"), builder.literal("$.tags")),
//                        "%" + tag + "%"));
//            }


            if (!filter.getStatuses().isEmpty()) {
                Predicate predicate = criteriaBuilder.or(root.get("status").in(filter.getStatuses()));
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
