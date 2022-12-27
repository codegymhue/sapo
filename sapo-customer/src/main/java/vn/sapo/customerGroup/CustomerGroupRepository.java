package vn.sapo.customerGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.sapo.entities.customer.CustomerGroup;

import java.util.Optional;

public interface CustomerGroupRepository  extends JpaRepository<CustomerGroup, Integer> {
    

}
