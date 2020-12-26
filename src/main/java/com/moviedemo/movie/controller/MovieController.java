package com.moviedemo.movie.controller;

import com.moviedemo.movie.dto.CreateMovieDTO;
import com.moviedemo.movie.dto.ListMovieDTO;
import com.moviedemo.movie.errors.InvalidCategoryException;
import com.moviedemo.movie.model.Movie;
import com.moviedemo.movie.repository.CategoryRepository;
import com.moviedemo.movie.repository.MovieRepository;
import com.moviedemo.movie.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<ListMovieDTO>> list(String title){
        List<ListMovieDTO> movies = movieService.findAll(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> listById(@PathVariable Long id){
        Optional<Movie> movie = movieService.findById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<ListMovieDTO>> listByCategory(@PathVariable String name) {
        List<ListMovieDTO> movies = movieService.findByCategoryName(name);
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Movie> create(@RequestBody @Valid CreateMovieDTO createMovieDTO, UriComponentsBuilder uriBuilder) {
        try {
            Movie newMovie = movieService.create(createMovieDTO);

            URI uri = uriBuilder.path("/{id}").buildAndExpand(newMovie.getId()).toUri();
            return ResponseEntity.created(uri).body(newMovie);
        } catch (InvalidCategoryException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Boolean deleted = movieService.deleteById(id);

        if(deleted) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody @Valid CreateMovieDTO createMovieDTO) {
        try {
            Movie updatedMovie = movieService.update(id, createMovieDTO);
            return ResponseEntity.ok(updatedMovie);
        } catch(InvalidCategoryException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
}
