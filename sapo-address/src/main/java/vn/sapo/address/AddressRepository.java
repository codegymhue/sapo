package vn.sapo.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    public void deleteByCustomerId(Integer customerId);

    List<Address> findAllByCustomerId(Integer customerId);

    List<Address> findAllBySupplierId(Integer supplierId);

    Page<Address> findAllBySupplierId(Pageable pageable, Integer supplierId);
    void deleteAllBySupplierId(Integer id);

}
