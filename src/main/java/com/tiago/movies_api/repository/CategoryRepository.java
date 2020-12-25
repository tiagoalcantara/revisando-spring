package com.tiago.movies_api.repository;

import com.tiago.movies_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
