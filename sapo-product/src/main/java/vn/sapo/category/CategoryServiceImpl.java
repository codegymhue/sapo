package vn.sapo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.category.dto.*;
import vn.sapo.entities.product.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResult> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResult findById(Integer id) {
        Category category = categoryRepository.findById(id).get();
        return categoryMapper.toDTO(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResult create(CreateCategoryParam createCategoryParam) {
        Category category = categoryRepository.save(categoryMapper.toModel(createCategoryParam));
        return categoryMapper.toDTO(category);
    }
}