package vn.sapo.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.customer.dto.CustomerResult;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.product.ProductStatus;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("FROM Customer as c   WHERE c.groupId = :groupId AND  c.status = :status ")
    List<Customer> findAllCustomerByTitleContainingAndStatus( Integer groupId,  CustomerStatus status);

}


