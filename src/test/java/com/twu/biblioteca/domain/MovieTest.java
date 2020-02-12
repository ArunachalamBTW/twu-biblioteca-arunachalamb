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

    @Test
    void shouldReturnAMovieDetailsWithSpecifiedSeparator() {
        String customSeparator = " - ";
        assertEquals(getMovieDetailsWithCustomSeparator(customSeparator), movie.getDetails(customSeparator));
    }

    public String getMovieDetails() {
        return getMovieDetailsWithCustomSeparator(MOVIE_DETAILS_SEPARATORS);

    }

    private String getMovieDetailsWithCustomSeparator(String customSeparator) {
        return movieName + customSeparator + year + customSeparator + director + customSeparator + rating;
    }


}