package vn.sapo.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.*;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {}