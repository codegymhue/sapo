package vn.sapo.tax;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.tax.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {
//    void deleteAllByProductId(Integer productId);
}
