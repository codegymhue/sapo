package vn.sapo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.sapo.entities.customer.Customer;

<<<<<<< HEAD
import java.util.*;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    List<Customer> findAllByGroupIdIn(List<Integer> groupIds);

    List<Customer> findAllByGroupIdAndStatus(Integer groupTitleId, CustomerStatus parseCustomerGroup);
=======
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {


    List<Customer> findAllByGroupIdIn(List<Integer> groupIds);
    List<Customer> findAllByEmployeeIdIn(List<Integer> employeeIds);
    List<Customer> findAllByGender(String genderId);


>>>>>>> cf9e56958713b8ee99a298cabdf1ddcf9488ab60
}


