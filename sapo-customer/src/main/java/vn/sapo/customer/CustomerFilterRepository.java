package vn.sapo.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.sapo.customer.dto.CustomerFilter;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface CustomerFilterRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    default Page<Customer> findAllByFilters(CustomerFilter filter, Pageable pageable) {
        return findAll((root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if(filter.getKeyword() != null){
                Predicate predicateFullName = criteriaBuilder.like(root.get("fullName"),'%' + filter.getKeyword() + '%');
                Predicate predicateCustomerCode = criteriaBuilder.like(root.get("customerCode"),'%' + filter.getKeyword() + '%');
                Predicate predicatePhoneNumber = criteriaBuilder.like(root.get("phoneNumber"),'%' + filter.getKeyword() + '%');
                Predicate predicateKw = criteriaBuilder.or(predicateCustomerCode, predicateFullName,predicatePhoneNumber);
                predicates.add(predicateKw);
            }
            if (!filter.getGroupIds().isEmpty()) {
                Predicate predicate = criteriaBuilder.or(root.get("group").get("id").in(filter.getGroupIds()));
                predicates.add(predicate);
            }

            if (filter.getGender() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("gender"), CustomerGender.parseCustomerGender(filter.getGender()));
                predicates.add(predicate);
            }

            Path<Instant> birthdayPath = root.get("birthday");
            Integer dayOfBirthDay = filter.getDayOfBirthday();
            Expression<Integer> dayOfBirthDayFunction = criteriaBuilder.function("day", Integer.class, birthdayPath);
            if (dayOfBirthDay != null) {
                Predicate predicate = criteriaBuilder.equal(dayOfBirthDayFunction, dayOfBirthDay);
                predicates.add(predicate);
            }

            Integer monthOfBirthday = filter.getMonthOfBirthday();
            Expression<Integer> monthOfBirthdayFunction = criteriaBuilder.function("month", Integer.class, birthdayPath);
            if (monthOfBirthday != null) {
                Predicate predicate = criteriaBuilder.equal(monthOfBirthdayFunction, monthOfBirthday);
                predicates.add(predicate);
            }

            if (!filter.getEmployeeIds().isEmpty()){
                Predicate predicate = criteriaBuilder.or(root.get("employee").get("id").in(filter.getEmployeeIds()));
                predicates.add(predicate);
            }

            if(!filter.getStatusList().isEmpty()){
                Predicate predicate = criteriaBuilder.or(root.get("status").in(filter.getStatusList()));
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


