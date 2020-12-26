package com.moviedemo.movie.repository;

import com.moviedemo.movie.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
