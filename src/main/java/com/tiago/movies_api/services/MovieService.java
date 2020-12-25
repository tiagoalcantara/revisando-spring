package com.tiago.movies_api.services;

import com.tiago.movies_api.dtos.CreateMovieDTO;
import com.tiago.movies_api.dtos.ListMovieDTO;
import com.tiago.movies_api.errors.InvalidCategoryException;
import com.tiago.movies_api.models.Category;
import com.tiago.movies_api.models.Movie;
import com.tiago.movies_api.repository.CategoryRepository;
import com.tiago.movies_api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ListMovieDTO> listMovies(String title) {
        List<Movie> listMovies;

        if(title == null) {
            listMovies = movieRepository.findAll();
        } else {
            listMovies = movieRepository.findByTitleContaining(title);
        }

        return ListMovieDTO.toMovieDTOList(listMovies);
    }

    public List<ListMovieDTO> listByCategory(String categoryName) {
        List<Movie> listMoviesByCategory = movieRepository.findByCategoryName(categoryName);

        return ListMovieDTO.toMovieDTOList(listMoviesByCategory);
    }

    public Movie createMovie(CreateMovieDTO createMovieDTO) throws InvalidCategoryException {
        Optional<Category> category = categoryRepository.findById(createMovieDTO.getCategoryId());

        if(category.isEmpty()) {
            throw new InvalidCategoryException();
        }

        Movie newMovie = createMovieDTO.toMovie();
        newMovie.setCategory(category.get());

        movieRepository.save(newMovie);

        return newMovie;
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(Movie movie, CreateMovieDTO createMovieDTO) throws InvalidCategoryException {
        Optional<Category> category = categoryRepository.findById(createMovieDTO.getCategoryId());

        if(category.isEmpty()) {
            throw new InvalidCategoryException();
        }
        movie.setCategory(category.get());
        movie.setRating(createMovieDTO.getRating());
        movie.setTitle(createMovieDTO.getTitle());
        movie.setPoster(createMovieDTO.getPoster());
        movie.setReleaseDate(createMovieDTO.getReleaseDate());

        return movie;
    }
}
