package com.moviedemo.movie.dto;

import com.moviedemo.movie.model.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListMovieDTO {
    private final String title;
    private final Date releaseDate;
    private final Double rating;

    public ListMovieDTO(Movie movie){
        this.title = movie.getTitle();
        this.releaseDate = movie.getReleaseDate();
        this.rating = movie.getRating();
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public static List<ListMovieDTO> toMovieList(List<Movie> movieList) {
        return movieList.stream().map(ListMovieDTO::new).collect(Collectors.toList());
    }
}
