package vn.sapo.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.category.dto.*;
import vn.sapo.entities.product.*;

@Component
public class CategoryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CategoryResult toDTO(Category category) {
        return modelMapper.map(category, CategoryResult.class);
    }

    public Category toModel(CreateCategoryParam createCategoryParam) {
        return modelMapper.map(createCategoryParam, Category.class);
    }

    public Category toModel(UpdateCategoryParam updateCategoryParam) {
        return modelMapper.map(updateCategoryParam, Category.class);
    }


}