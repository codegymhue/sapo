package vn.sapo.controllers.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sapo.category.*;
import vn.sapo.category.dto.CategoryResult;
import vn.sapo.category.dto.CreateCategoryParam;

import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryAPI {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping("")
    public ResponseEntity<?> showAllCategory() {
        List<CategoryResult> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryParam createCategoryParam) {
        CategoryResult category = categoryService.create(createCategoryParam);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
