package com.tiago.movies_api.controllers;

import com.tiago.movies_api.dtos.CreateMovieDTO;
import com.tiago.movies_api.dtos.ListMovieDTO;
import com.tiago.movies_api.errors.InvalidCategoryException;
import com.tiago.movies_api.models.Movie;
import com.tiago.movies_api.services.MovieService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<ListMovieDTO>> listMovies(String title) {
        List<ListMovieDTO> list = movieService.listMovies(title);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ListMovieDTO>> listByCategory(@PathVariable String categoryName) {
        List<ListMovieDTO> listMoviesByCategory = movieService.listByCategory(categoryName);

        return ResponseEntity.ok(listMoviesByCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> listDetails(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Movie> createMovie(@RequestBody @Valid CreateMovieDTO createMovieDTO, UriComponentsBuilder uriBuilder) {
      try {
          Movie newMovie = movieService.createMovie(createMovieDTO);
          URI uri = uriBuilder.path("/movie/{id}").buildAndExpand(newMovie.getId()).toUri();
          return ResponseEntity.created(uri).body(newMovie);
      } catch (InvalidCategoryException exception) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
      }
    };

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()) {
            movieService.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody @Valid CreateMovieDTO createMovieDTO) {
        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()) {
            try {
                return ResponseEntity.ok(movieService.updateMovie(movie.get(), createMovieDTO));
            } catch (InvalidCategoryException exception) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
            }

        }

        return ResponseEntity.notFound().build();
    }
}
