package com.tiago.movies_api.controllers;

import com.tiago.movies_api.dtos.CreateCategoryDTO;
import com.tiago.movies_api.models.Category;
import com.tiago.movies_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listCategories() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> listCategoryById (@PathVariable Long id){
        Optional<Category> category = categoryService.listCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CreateCategoryDTO createCategoryDTO, UriComponentsBuilder uriBuilder) {
       Category newCategory = categoryService.createCategory(createCategoryDTO);

        URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(newCategory.getId()).toUri();
        return ResponseEntity.created(uri).body(newCategory);
    }

}
