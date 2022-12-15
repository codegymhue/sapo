package vn.sapo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.category.dto.*;
import vn.sapo.entities.product.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResult> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResult> categoryResults = new ArrayList<>();
        for(Category category : categories){
            categoryResults.add(categoryMapper.toDTO(category));
        }
        return categoryResults;
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).get();
    }



    @Override
    public Category save(Category category) {
        category.setId(0);
        return categoryRepository.save(category);
    }

    @Override
    public void remove(Integer id) {

    }

//    @Override
//    public CategoryResult createCategoryResult(CategoryCreateParam categoryCreateParam) {
//        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toModel(categoryCreateParam)));
//    }
    @Override
    public CategoryResult create(CategoryParam categoryParam) {
        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toModel(categoryParam)));
    }
}