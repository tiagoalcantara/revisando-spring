package com.moviedemo.movie.controller;

import com.moviedemo.movie.dto.CreateCategoryDTO;
import com.moviedemo.movie.model.Category;
import com.moviedemo.movie.services.CategoryService;
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
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Category> create(@RequestBody @Valid CreateCategoryDTO createCategoryDTO, UriComponentsBuilder uriBuilder) {
        Category newCategory = categoryService.create(createCategoryDTO);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(newCategory.getId()).toUri();
        return ResponseEntity.created(uri).body(newCategory);
    }
}
