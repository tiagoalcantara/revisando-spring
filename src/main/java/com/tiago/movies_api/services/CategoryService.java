package com.tiago.movies_api.services;

import com.tiago.movies_api.dtos.CreateCategoryDTO;
import com.tiago.movies_api.models.Category;
import com.tiago.movies_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> listCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return category;
    }

    public Category createCategory(CreateCategoryDTO createCategoryDTO) {
        Category newCategory = createCategoryDTO.toCategory();
        categoryRepository.save(newCategory);

        return newCategory;
    }
}
