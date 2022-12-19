package vn.sapo.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.product.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}