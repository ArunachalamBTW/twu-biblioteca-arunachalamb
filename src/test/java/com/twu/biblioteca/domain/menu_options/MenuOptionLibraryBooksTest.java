package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.domain.menu_options.MenuOptionLibraryBooks;
import com.twu.biblioteca.domain.menu_options.MenuOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.twu.biblioteca.config.GlobalConstants.BOOK_DETAILS_SEPARATORS;
import static com.twu.biblioteca.config.GlobalConstants.NEW_LINE;
import static org.junit.jupiter.api.Assertions.*;

class MenuOptionLibraryBooksTest {

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
        Screen.reset();
    }

    @Test
    void shouldDisplayBooksInALibrary() {
        InputStream sysInBackup = System.in;
        MenuOptions menuOption = new MenuOptionLibraryBooks();

        menuOption.execute(library);

        assertEquals(defaultBookListDetails() + NEW_LINE + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    public String defaultBookListDetails() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Martin Fowler\n" +
                "2. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }

}