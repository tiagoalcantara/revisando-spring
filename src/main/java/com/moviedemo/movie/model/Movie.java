package com.moviedemo.movie.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Temporal(TemporalType.DATE) @Column(nullable = false)
    private Date releaseDate;
    @Column(nullable = false)
    private String poster;
    @Column(nullable = false)
    private Double rating;
    @ManyToOne
    private Category category;

    public Movie(){}

    public Movie(String title, Date releaseDate, String poster, Double rating, Category category) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.rating = rating;
        this.category = category;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
