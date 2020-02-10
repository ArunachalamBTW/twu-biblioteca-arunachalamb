package com.twu.biblioteca.domain;

import static com.twu.biblioteca.config.GlobalConstants.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.twu.biblioteca.domain.DomainConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();
    private static List<Book> books;

    @BeforeAll
    public static void initTest() {
        Book book1 = new Book("Programming Book 1", 2000, "Martin Fowler");
        Book book2 = new Book("Programming Book 2", 2001, "Martin Fowler");
        books = new ArrayList<>(Arrays.asList(book1, book2));
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @Test
    void shouldShowOnlyOneBookInLibrary() {
        Book book = new Book("Programming Book 1", 2000, "Martin Fowler");
        Library library = new Library(Collections.singletonList(book));

        assertEquals(getFirstBookDetails() + NEW_LINE, library.getBooks());
    }

    @Test
    void shouldShowAllBooksInALibrary() { // TODO - is this the simplest test?
        Library library = new Library(books); // TODO - intent behind helper method is probably good, however its creating more problem then helping at this point in time.

        assertEquals(defaultBooksListDetails() + NEW_LINE, library.getBooks()); // TODO - why trim? Apple does not fall. Change your physics.
    }

    @Test
    void shouldCheckOutBookWithSameName() {
        Library library = new Library(books);
        String bookDetails = "";
        bookDetails += library.getBooks();

        library.checkout("Programming Book 1");

        bookDetails += library.getBooks();
        assertEquals(SUCCESS_CHECKOUT_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListDetails() + NEW_LINE + getSecondBookDetails() + NEW_LINE, bookDetails);
    }

    @Test
    void shouldFailWhenWithSameNameIsNotFound() {
        Library library = new Library(books);
        String bookDetails = "";

        bookDetails += library.getBooks();
        library.checkout("Random Book");
        bookDetails += library.getBooks();

        assertEquals(FAIL_CHECKOUT_MESSAGE + NEW_LINE, consoleOutContent.toString());
        assertEquals(defaultBooksListDetails() + NEW_LINE + defaultBooksListDetails() + NEW_LINE, bookDetails);
    }

    @Test
    void shouldReturnTheBookToTheLibrary() {
        String programmingBook = "Programming Book 1";
        Library library = new Library(books);
        String bookDetails = "";

        bookDetails += library.getBooks();
        library.checkout(programmingBook);
        bookDetails += library.getBooks();
        library.returnBook(programmingBook);
        bookDetails += library.getBooks();

        assertEquals(SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + SUCCESS_RETURN_MESSAGE + NEW_LINE, consoleOutContent.toString());
        assertEquals(defaultBooksListDetails() + NEW_LINE + getSecondBookDetails() + NEW_LINE + defaultBooksListDetails() + NEW_LINE, bookDetails);
    }

    @Test
    void shouldNotReturnAWrongBook() {
        String programmingBook = "Programming Book 1";
        String wrongBook = "Prog Book 1";
        Library library = new Library(books);
        String bookDetails = "";

        bookDetails += library.getBooks();
        library.checkout(programmingBook);
        bookDetails += library.getBooks();
        library.returnBook(wrongBook);
        bookDetails += library.getBooks();

        assertEquals(SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + FAIL_RETURN_MESSAGE + NEW_LINE, consoleOutContent.toString());
        assertEquals(defaultBooksListDetails() + NEW_LINE + getSecondBookDetails() + NEW_LINE + getSecondBookDetails() + NEW_LINE, bookDetails);
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

}