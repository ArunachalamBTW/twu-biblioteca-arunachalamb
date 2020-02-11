package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.domain.menu_options.MenuOptionCheckout;
import com.twu.biblioteca.domain.menu_options.MenuOptions;
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

import static com.twu.biblioteca.config.GlobalConstants.*;
import static com.twu.biblioteca.domain.DomainConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuOptionCheckoutTest {
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
        Screen.reset();
    }

    @Test
    void shouldCheckoutABookFromLibrary() {
        InputStream sysInBackup = System.in;
        String bookName = "Programming Book 1";
        ByteArrayInputStream input1 = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(input1);
        MenuOptions checkoutBook = new MenuOptionCheckout();

        checkoutBook.execute(library);

        assertEquals(GET_BOOK_NAME + NEW_LINE + SUCCESS_CHECKOUT_MESSAGE + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    public String defaultBookListDetails() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Martin Fowler\n" +
                "2. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }

}