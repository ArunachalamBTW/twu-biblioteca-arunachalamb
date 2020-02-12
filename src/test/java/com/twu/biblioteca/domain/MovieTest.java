package com.twu.biblioteca.domain;

import org.junit.jupiter.api.Test;

import static com.twu.biblioteca.config.GlobalConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private String movieName = "Interstellar";
    private int year = 2020;
    private String director = "Christopher Nolan";
    private float rating = 10;
    private Movie movie = new Movie(movieName, year, director, rating);

    @Test
    void shouldReturnAMovieDetails() {
        assertEquals(getMovieDetails(), movie.getDetails());
    }

    public String getMovieDetails() {
        return movieName + MOVIE_DETAILS_SEPARATORS + year + MOVIE_DETAILS_SEPARATORS + director + MOVIE_DETAILS_SEPARATORS + rating;
    }



}