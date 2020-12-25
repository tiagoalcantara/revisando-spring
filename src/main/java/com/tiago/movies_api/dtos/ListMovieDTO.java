package com.tiago.movies_api.dtos;

import com.tiago.movies_api.models.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class ListMovieDTO {
    private final String title;
    private final String poster;

    public ListMovieDTO(Movie movie) {
        this.title = movie.getTitle();
        this.poster = movie.getPoster();
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public static List<ListMovieDTO> toMovieDTOList(List<Movie> movieList) {
        return movieList.stream().map(ListMovieDTO::new).collect(Collectors.toList());
    }
}
