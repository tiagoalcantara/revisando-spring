package com.tiago.movies_api.dtos;

import com.tiago.movies_api.models.Category;
import com.tiago.movies_api.models.Movie;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Movie toMovie(){
        Movie movie = new Movie();
        movie.setTitle(this.title);
        movie.setReleaseDate(this.releaseDate);
        movie.setPoster(this.poster);
        movie.setRating(this.rating);
        return movie;
    }
}
