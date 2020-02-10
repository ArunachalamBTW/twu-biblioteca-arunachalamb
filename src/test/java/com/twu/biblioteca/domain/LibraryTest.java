package com.twu.biblioteca.domain;

import static com.twu.biblioteca.config.CONSTANTS.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        assertEquals(getFirstBookDetails() + NEW_LINE, library.displayAllBooks());
    }

    @Test
    void shouldShowAllBooksInALibrary() { // TODO - is this the simplest test?
        Library library = new Library(books); // TODO - intent behind helper method is probably good, however its creating more problem then helping at this point in time.

        assertEquals(defaultBooksListDetails(), library.displayAllBooks().trim()); // TODO - why trim? Apple does not fall. Change your physics.
    }

    @Test
    void shouldCheckOutBookWithSameName() {
        Library library = new Library(books);
        String bookDetails = "";
        bookDetails += library.displayAllBooks();

        library.checkOutBook("Programming Book 1");

        bookDetails += library.displayAllBooks();
        assertEquals(SUCCESS_CHECKOUT_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListDetails() + NEW_LINE + getSecondBookDetails(), bookDetails.trim());
    }

    @Test
    void shouldFailWhenWithSameNameIsNotFound() {
        Library library = new Library(books);
        String bookDetails = "";

        bookDetails += library.displayAllBooks();
        library.checkOutBook("Random Book");
        bookDetails += library.displayAllBooks();

        assertEquals(FAIL_CHECKOUT_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListDetails() + NEW_LINE + defaultBooksListDetails(), bookDetails.trim());
    }

    @Test
    void shouldReturnTheBookToTheLibrary() {
        String programmingBook = "Programming Book 1";
        Library library = new Library(books);
        String bookDetails = "";

        bookDetails += library.displayAllBooks();
        library.checkOutBook(programmingBook);
        bookDetails += library.displayAllBooks();
        library.returnBook(programmingBook);
        bookDetails += library.displayAllBooks();

        assertEquals(SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + SUCCESS_RETURN_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListDetails() + NEW_LINE + getSecondBookDetails() + NEW_LINE + defaultBooksListDetails(), bookDetails.trim());
    }

    @Test
    void shouldNotReturnAWrongBook() {
        String programmingBook = "Programming Book 1";
        String wrongBook = "Prog Book 1";
        Library library = new Library(books);
        String bookDetails = "";

        bookDetails += library.displayAllBooks();
        library.checkOutBook(programmingBook);
        bookDetails += library.displayAllBooks();
        library.returnBook(wrongBook);
        bookDetails += library.displayAllBooks();

        assertEquals(SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + FAIL_RETURN_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListDetails() + NEW_LINE + getSecondBookDetails() + NEW_LINE + getSecondBookDetails(), bookDetails.trim());
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