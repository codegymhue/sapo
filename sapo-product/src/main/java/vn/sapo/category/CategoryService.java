package vn.sapo.category;

import vn.sapo.category.dto.*;
import vn.sapo.entities.product.*;

import java.util.*;

public interface CategoryService {

    List<CategoryResult> findAll();

    CategoryResult findById(Integer id);
    void deleteById(Integer id);

    CategoryResult create(CreateCategoryParam createCategoryParam);
}
