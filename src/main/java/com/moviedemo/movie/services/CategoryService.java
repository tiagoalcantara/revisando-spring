package com.moviedemo.movie.services;

import com.moviedemo.movie.dto.CreateCategoryDTO;
import com.moviedemo.movie.model.Category;
import com.moviedemo.movie.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category create(CreateCategoryDTO createCategoryDTO){
        Category newCategory = createCategoryDTO.toCategory();
        categoryRepository.save(newCategory);

        return newCategory;
    }
}
