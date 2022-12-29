package vn.sapo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.category.dto.*;
import vn.sapo.entities.product.*;
import vn.sapo.shared.exceptions.DataInputException;

import java.util.ArrayList;
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
        List<CategoryResult> result = new ArrayList<>();
         result = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
        return result;
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
        if (categoryRepository.findByTitle(createCategoryParam.getTitle().trim()).isPresent()) {
            throw new DataInputException("Loại sản phẩm đã tồn tại. Vui lòng kiểm tra!!!");
        }
        Category category = categoryRepository.save(categoryMapper.toModel(createCategoryParam));
        return categoryMapper.toDTO(category);
    }
}