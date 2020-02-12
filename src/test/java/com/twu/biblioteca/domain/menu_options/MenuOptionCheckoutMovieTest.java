package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.domain.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuOptionCheckoutMovieTest {
    private ByteArrayOutputStream consoleOutContent;
    private PrintStream printStream;
    private List<Book> books;
    private Library library;
    private Screen screen = Screen.getInstance();
    private Library mockedLibrary;
    private List<Movie> movies;

    @BeforeEach
    public void setUpStreams() {
        printStream = System.out;
        consoleOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutContent));
        mockedLibrary = mock(Library.class);

        Book book1 = new Book("Programming Book 1", 2000, "Martin Fowler");
        Book book2 = new Book("Programming Book 2", 2001, "Martin Fowler");
        books = new ArrayList<>(Arrays.asList(book1, book2));

        Movie movie1 = new Movie("Interstellar", 2020, "Christopher Nolan", 10);
        Movie movie2 = new Movie("2.0", 2019, "Shankar", 10);
        movies = new ArrayList<>(Arrays.asList(movie1, movie2));

        library = new Library(books, movies, screen);
    }

    @AfterEach
    public void setOutStreams() {
        System.setOut(printStream);
        Screen.reset();
    }

    @Test
    void shouldCheckoutABookFromLibrary() {
        InputStream sysInBackup = System.in;
        String movieName = "Interstellar";
        ByteArrayInputStream input1 = new ByteArrayInputStream(movieName.getBytes());
        System.setIn(input1);
        MenuOptions checkoutMovie = new MenuOptionCheckoutMovie();

        checkoutMovie.execute(mockedLibrary);

        verify(mockedLibrary, times(1)).checkoutMovie(movieName);
        System.setIn(sysInBackup);
    }

}