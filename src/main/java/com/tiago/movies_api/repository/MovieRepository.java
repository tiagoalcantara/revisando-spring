package com.tiago.movies_api.repository;

import com.tiago.movies_api.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContaining(String title);
    List<Movie> findByCategoryName(String categoryName);
}
