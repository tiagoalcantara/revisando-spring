package com.moviedemo.movie.services;

import com.moviedemo.movie.dto.CreateMovieDTO;
import com.moviedemo.movie.dto.ListMovieDTO;
import com.moviedemo.movie.errors.InvalidCategoryException;
import com.moviedemo.movie.model.Category;
import com.moviedemo.movie.model.Movie;
import com.moviedemo.movie.repository.CategoryRepository;
import com.moviedemo.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ListMovieDTO> findAll(String title){
        List<Movie> movies;

        if(title == null) {
            movies = movieRepository.findAll();
        } else {
            movies = movieRepository.findByTitleContaining(title);
        }

        return ListMovieDTO.toMovieList(movies);
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    public List<ListMovieDTO> findByCategoryName(String name) {
        List<Movie> movies = movieRepository.findByCategoryName(name);
        return ListMovieDTO.toMovieList(movies);
    }
    public Movie create(CreateMovieDTO createMovieDTO) throws InvalidCategoryException {
        Optional<Category> newMovieCategory = categoryRepository.findById(createMovieDTO.getCategoryId());

        if(newMovieCategory.isEmpty()) {
            throw new InvalidCategoryException();
        }

        Movie newMovie = createMovieDTO.toMovie();
        newMovie.setCategory(newMovieCategory.get());

        movieRepository.save(newMovie);

        return newMovie;
    }

    public Boolean deleteById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if(movie.isEmpty()){
            return false;
        }

        movieRepository.deleteById(id);
        return true;
    }

    public Movie update(Long id, CreateMovieDTO createMovieDTO) throws InvalidCategoryException {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty()) {
            // Gambiarra, criem uma exceção específica
            throw new InvalidCategoryException();
        }

        Optional<Category> category = categoryRepository.findById(createMovieDTO.getCategoryId());
        if(category.isEmpty()) {
            throw new InvalidCategoryException();
        }


        Movie movieToUpdate = movie.get();
        movieToUpdate.setTitle(createMovieDTO.getTitle());
        movieToUpdate.setRating(createMovieDTO.getRating());
        movieToUpdate.setPoster(createMovieDTO.getPoster());
        movieToUpdate.setReleaseDate(createMovieDTO.getReleaseDate());
        movieToUpdate.setCategory(category.get());

        return movieToUpdate;
    }
}
