package vn.sapo.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    public void deleteByCustomerId(Integer customerId);

    List<Address> findAllByCustomerId(Integer customerId);


}
