package com.twu.biblioteca.domain;

import static com.twu.biblioteca.config.GlobalConstants.*;

public class Movie {
    private String name;
    private int year;
    private String director;
    private float rating;

    public Movie(String name, int year, String director, float rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getDetails() {
        return name + MOVIE_DETAILS_SEPARATORS + year + MOVIE_DETAILS_SEPARATORS + director + MOVIE_DETAILS_SEPARATORS + rating;
    }
}
