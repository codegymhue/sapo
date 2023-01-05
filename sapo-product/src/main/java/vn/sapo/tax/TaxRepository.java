package vn.sapo.tax;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.tax.Tax;

import java.util.Optional;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {
    Optional<Tax> findByTitle(String title);
}
