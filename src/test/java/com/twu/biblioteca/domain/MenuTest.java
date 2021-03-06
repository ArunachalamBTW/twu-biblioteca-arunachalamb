package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.controller.Login;
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

import static com.twu.biblioteca.config.GlobalConstants.*;
import static com.twu.biblioteca.console.ConsoleConstants.MENU_OPTIONS;
import static com.twu.biblioteca.controller.ControllerConstants.INVALID_OPTION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MenuTest {
    private ByteArrayOutputStream consoleOutContent;
    private PrintStream printStream;
    private List<Book> books;
    private Library library;
    private List<Movie> movies;
    private Login login;

    @BeforeEach
    public void setUpStreams() {
        printStream = System.out;
        consoleOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutContent));

        Book book1 = new Book("Programming Book 1", 2000, "Martin Fowler");
        Book book2 = new Book("Programming Book 2", 2001, "Martin Fowler");
        books = new ArrayList<>(Arrays.asList(book1, book2));

        Movie movie1 = new Movie("Interstellar", 2020, "Christopher Nolan", 10);
        Movie movie2 = new Movie("2.0", 2019, "Shankar", 10);
        movies = new ArrayList<>(Arrays.asList(movie1, movie2));

        login = mock(Login.class);

        library = new Library(books, movies, Screen.getInstance());
    }

    @AfterEach
    public void setOutStreams() {
        System.setOut(printStream);
        Input.reset();
        Screen.reset();
    }

    @Test
    void shouldSelectFirstMenuOptionToListBooks() {
        InputStream sysInBackup = System.in;
        String input = "1" + NEW_LINE + MAIN_MENU_QUIT;
        ByteArrayInputStream input1 = new ByteArrayInputStream(input.getBytes());
        System.setIn(input1);
        Menu menu = new Menu(library, Screen.getInstance());

        menu.mainMenu(login);

        assertEquals(MENU_OPTIONS + NEW_LINE + defaultBookListDetails() + NEW_LINE + NEW_LINE + MENU_OPTIONS + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    @Test
    void shouldNotifyWhenInvalidOptionIsSelected() {
        InputStream sysInBackup = System.in;
        String input = "9" + NEW_LINE + MAIN_MENU_QUIT;
        ByteArrayInputStream input1 = new ByteArrayInputStream(input.getBytes());
        System.setIn(input1);
        Menu menu = new Menu(library, Screen.getInstance());

        menu.mainMenu(login);

        assertEquals(MENU_OPTIONS + NEW_LINE + INVALID_OPTION + NEW_LINE + MENU_OPTIONS + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    @Test
    void shouldReturnInvalidOptionIfStringIsEnteredInMenuOption() {
        InputStream sysInBackup = System.in;
        String input = "asdfawer\n" + MAIN_MENU_QUIT;
        ByteArrayInputStream input1 = new ByteArrayInputStream(input.getBytes());
        System.setIn(input1);
        Menu menu = new Menu(library, Screen.getInstance());

        menu.mainMenu(login);

        assertEquals(MENU_OPTIONS + NEW_LINE + INVALID_OPTION + NEW_LINE + MENU_OPTIONS + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    public String defaultBookListDetails() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Martin Fowler\n" +
                "2. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }
}