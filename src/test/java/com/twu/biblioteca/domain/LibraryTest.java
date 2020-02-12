package com.twu.biblioteca.domain;

import static com.twu.biblioteca.config.GlobalConstants.*;

import com.twu.biblioteca.console.Screen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static com.twu.biblioteca.domain.DomainConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();
    private static List<Book> books;
    private static List<Movie> movies;
    Movie movie = new Movie("Interstellar", 2020, "Christopher Nolan", 10);

    @BeforeAll
    public static void initTest() {
        Book book1 = new Book("Programming Book 1", 2000, "Martin Fowler");
        Book book2 = new Book("Programming Book 2", 2001, "Martin Fowler");
        books = new ArrayList<>(Arrays.asList(book1, book2));

        Movie movie1 = new Movie("Interstellar", 2020, "Christopher Nolan", 10);
        Movie movie2 = new Movie("2.0", 2019, "Shankar", 10);
        movies = new ArrayList<>(Arrays.asList(movie1, movie2));
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @AfterEach
    public void tearDown() {
        Screen.reset();
    }

    @Test
    void shouldShowOnlyOneBookInLibrary() {
        Book book = new Book("Programming Book 1", 2000, "Martin Fowler");
        Library library = new Library(Collections.singletonList(book), movies, Screen.getInstance());

        assertEquals(getFirstBookDetails() + NEW_LINE, library.getAllBooks());
    }

    @Test
    void shouldShowOnlyOneMovieInLibrary() {
        Library library = new Library(new ArrayList<>(), Collections.singletonList(movie), Screen.getInstance());

        assertEquals("1. " + getFirstMovieDetails() + NEW_LINE, library.getAllMovies());
    }

    @Test
    void shouldShowAllBooksInALibrary() { // TODO - is this the simplest test?
        Library library = new Library(books, movies, Screen.getInstance()); // TODO - intent behind helper method is probably good, however its creating more problem then helping at this point in time.

        assertEquals(defaultBooksListDetails() + NEW_LINE, library.getAllBooks()); // TODO - why trim? Apple does not fall. Change your physics.
    }

    @Test
    void shouldShowAllMoviesInLibrary() {
        Library library = new Library(new ArrayList<>(), movies, Screen.getInstance());

        assertEquals(defaultMoviesListDetails() + NEW_LINE, library.getAllMovies());
    }

    @Test
    void shouldNotCheckOutWithoutLogin() {
        Library library = new Library(books, movies, Screen.getInstance());
        String bookDetails = "";
        bookDetails += library.getAllBooks();

        library.checkoutBook("Programming Book 1");

        bookDetails += library.getAllBooks();
        assertEquals(CHECKOUT_WITHOUT_LOGIN, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListDetails() + NEW_LINE + defaultBooksListDetails() + NEW_LINE, bookDetails);
    }

    @Test
    void shouldCheckOutMovieWithSameName() {
        Library library = new Library(books, movies, Screen.getInstance());
        String movieDetails = "";
        movieDetails += library.getAllMovies();

        library.checkoutMovie("Interstellar");

        movieDetails += library.getAllMovies();
        assertEquals(SUCCESS_CHECKOUT_MESSAGE_FOR_MOVIE, consoleOutContent.toString().trim());
        assertEquals(defaultMoviesListDetails() + NEW_LINE + "1" + PERIOD + SPACE + getSecondMovieDetails() + NEW_LINE, movieDetails);
    }

    @Test
    void shouldFailWhenBookWithSameNameIsNotFound() {
        Library library = new Library(books, movies, Screen.getInstance());
        String bookDetails = "";

        bookDetails += library.getAllBooks();
        library.checkoutBook("Random Book");
        bookDetails += library.getAllBooks();

        assertEquals(FAIL_CHECKOUT_MESSAGE_FOR_BOOK + NEW_LINE, consoleOutContent.toString());
        assertEquals(defaultBooksListDetails() + NEW_LINE + defaultBooksListDetails() + NEW_LINE, bookDetails);
    }

    @Test
    void shouldFailWhenMovieWithSameNameIsNotFound() {
        Library library = new Library(books, movies, Screen.getInstance());
        String movieDetails = "";

        movieDetails += library.getAllMovies();
        library.checkoutMovie("Prestige");
        movieDetails += library.getAllMovies();

        assertEquals(FAIL_CHECKOUT_MESSAGE_FOR_MOVIE + NEW_LINE, consoleOutContent.toString());
        assertEquals(defaultMoviesListDetails() + NEW_LINE + defaultMoviesListDetails() + NEW_LINE, movieDetails);
    }

    @Test
    void shouldNotReturnTheBookToTheLibraryWithoutCheckout() {
        String programmingBook = "Programming Book 1";
        Library library = new Library(books, movies, Screen.getInstance());
        String bookDetails = "";

        bookDetails += library.getAllBooks();
        library.checkoutBook(programmingBook);
        bookDetails += library.getAllBooks();
        library.returnBook(programmingBook);
        bookDetails += library.getAllBooks();

        assertEquals(CHECKOUT_WITHOUT_LOGIN + NEW_LINE + FAIL_RETURN_MESSAGE_FOR_BOOK + NEW_LINE, consoleOutContent.toString());
        assertEquals(defaultBooksListDetails() + NEW_LINE + defaultBooksListDetails() + NEW_LINE + defaultBooksListDetails() + NEW_LINE, bookDetails);
    }

    @Test
    void shouldNotReturnAWrongBookWithoutLogin() {
        String programmingBook = "Programming Book 1";
        String wrongBook = "Prog Book 1";
        Library library = new Library(books, movies, Screen.getInstance());
        String bookDetails = "";

        bookDetails += library.getAllBooks();
        library.checkoutBook(programmingBook);
        bookDetails += library.getAllBooks();
        library.returnBook(wrongBook);
        bookDetails += library.getAllBooks();

        assertEquals(CHECKOUT_WITHOUT_LOGIN + NEW_LINE + FAIL_RETURN_MESSAGE_FOR_BOOK + NEW_LINE, consoleOutContent.toString());
        assertEquals(defaultBooksListDetails() + NEW_LINE + defaultBooksListDetails() + NEW_LINE + defaultBooksListDetails() + NEW_LINE, bookDetails);
    }

    public String defaultBooksListDetails() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Martin Fowler\n" +
                "2. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }

    public String getFirstBookDetails() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }

    public String getSecondBookDetails() {
        return "1. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }

    public String defaultMoviesListDetails() {
        return "1. " + getFirstMovieDetails() + NEW_LINE + "2. " + getSecondMovieDetails();
    }

    public String getFirstMovieDetails() {
        String movieName = "Interstellar";
        String director = "Christopher Nolan";
        int year = 2020;
        float rating = 10;
        String separator = MOVIE_DETAILS_SEPARATORS;
        return movieName + separator + year + separator + director + separator + rating;
    }

    public String getSecondMovieDetails() {
        String movieName = "2.0";
        String director = "Shankar";
        int year = 2019;
        float rating = 10;
        String separator = MOVIE_DETAILS_SEPARATORS;
        return movieName + separator + year + separator + director + separator + rating;
    }

}