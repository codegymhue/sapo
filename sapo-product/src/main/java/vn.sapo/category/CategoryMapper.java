package vn.sapo.category;

import org.springframework.stereotype.Component;
import vn.sapo.category.dto.*;
import vn.sapo.entities.product.*;

@Component
public class CategoryMapper {

    public CategoryResult toDTO(Category category) {
        return new CategoryResult()
        .setId(category.getId())
        .setName(category.getName());
    }

    public Category toModel(CategoryParam categoryParam) {
        return new Category()
        .setId(categoryParam.getId())
        .setName(categoryParam.getName());
    }

    public Category toModel(CategoryCreateParam categoryCreateParam) {
        return new Category().setName(categoryCreateParam.getName());
    }

}