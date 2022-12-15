package vn.sapo.category;

import vn.sapo.category.dto.*;
import vn.sapo.entities.product.*;

import java.util.*;

public interface CategoryService {

//    public CategoryResult createCategoryResult(CategoryCreateParam categoryCreateParam);

    List<CategoryResult> findAll();

    Category findById(Integer id);

    Category save(Category category);

    void remove(Integer id);

    CategoryResult create(CategoryParam categoryParam);
}
