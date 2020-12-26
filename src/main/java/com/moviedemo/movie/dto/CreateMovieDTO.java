package com.moviedemo.movie.dto;

import com.moviedemo.movie.model.Movie;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateMovieDTO {
    @NotNull
    private String title;
    @NotNull
    private Date releaseDate;
    @NotNull
    private String poster;
    @NotNull @Min(0) @Max(10)
    private Double rating;
    @NotNull
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }


    public String getPoster() {
        return poster;
    }

    public Double getRating() {
        return rating;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Movie toMovie(){
        Movie movie = new Movie();
        movie.setPoster(this.poster);
        movie.setRating(this.rating);
        movie.setReleaseDate(this.releaseDate);
        movie.setTitle(this.title);

        return movie;
    }
}
