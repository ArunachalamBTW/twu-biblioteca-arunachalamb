package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.controller.BibliotecaApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.twu.biblioteca.config.GlobalConstants.BOOK_DETAILS_SEPARATORS;
import static com.twu.biblioteca.config.GlobalConstants.NEW_LINE;
import static com.twu.biblioteca.console.ConsoleConstants.MENU_OPTIONS;
import static com.twu.biblioteca.console.ConsoleConstants.WELCOME_MESSAGE;
import static com.twu.biblioteca.controller.ControllerConstants.INVALID_OPTION;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private ByteArrayOutputStream consoleOutContent;
    private PrintStream printStream;
    private static List<Book> books;
    private static Library library;

    @BeforeAll
    public static void initTest() {
        Book book1 = new Book("Programming Book 1", 2000, "Martin Fowler");
        Book book2 = new Book("Programming Book 2", 2001, "Martin Fowler");
        books = new ArrayList<>(Arrays.asList(book1, book2));
        library = new Library(books);
    }

    @BeforeEach
    public void setUpStreams() {
        printStream = System.out;
        consoleOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutContent));
    }

    @AfterEach
    public void setOutStreams() {
        System.setOut(printStream);
        Input.reset();
    }

    @Test
    void shouldSelectFirstMenuOptionToListBooks() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String input = "1" + NEW_LINE + "4";
        ByteArrayInputStream input1 = new ByteArrayInputStream(input.getBytes());
        System.setIn(input1);
        Menu menu = new Menu(library);

        menu.mainMenu();

        assertEquals(MENU_OPTIONS + NEW_LINE + defaultBooksListString() + NEW_LINE + NEW_LINE + MENU_OPTIONS + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    @Test
    void shouldNotifyWhenInvalidOptionIsSelected() {
        InputStream sysInBackup = System.in;
        String input = "9" + NEW_LINE + "4";
        ByteArrayInputStream input1 = new ByteArrayInputStream(input.getBytes());
        System.setIn(input1);
        Menu menu = new Menu(library);

        menu.mainMenu();

        assertEquals(MENU_OPTIONS + NEW_LINE + INVALID_OPTION + NEW_LINE + MENU_OPTIONS + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    public String defaultBooksListString() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Martin Fowler\n" +
                "2. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }
}