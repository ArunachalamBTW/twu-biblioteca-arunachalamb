package com.twu.biblioteca;

import static com.twu.biblioteca.config.CONSTANTS.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @Test
    void shouldShowAllBooksInALibrary() {
        Library library = new Library(generateTempBooks());

        library.displayAllBooks();

        assertEquals(defaultBooksListString(), consoleOutContent.toString().trim());
    }

    @Test
    void shouldCheckOutBookWithSameName() {
        Library library = new Library(generateTempBooks());

        library.displayAllBooks();
        library.checkOutBook("Programming Book 1");
        library.displayAllBooks();

        assertEquals(defaultBooksListString() + NEW_LINE + SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + getSecondBookString(), consoleOutContent.toString().trim());
    }

    @Test
    void shouldFailWhenWithSameNameIsNotFound() {
        Library library = new Library(generateTempBooks());

        library.displayAllBooks();
        library.checkOutBook("Random Book");
        library.displayAllBooks();

        assertEquals(defaultBooksListString() + NEW_LINE + FAIL_CHECKOUT_MESSAGE + NEW_LINE + defaultBooksListString(), consoleOutContent.toString().trim());
    }

    @Test
    void shouldReturnTheBookToTheLibrary() {
        String programmingBook = "Programming Book 1";
        Library library = new Library(generateTempBooks());

        library.displayAllBooks();
        library.checkOutBook(programmingBook);
        library.displayAllBooks();
        library.returnBook(programmingBook);
        library.displayAllBooks();

        assertEquals(defaultBooksListString() + NEW_LINE + SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + getSecondBookString() + NEW_LINE + SUCCESS_RETURN_MESSAGE + NEW_LINE + defaultBooksListString(), consoleOutContent.toString().trim());
    }

    @Test
    void shouldNotReturnAWrongBook() {
        String programmingBook = "Programming Book 1";
        String wrongBook = "Prog Book 1";
        Library library = new Library(generateTempBooks());

        library.displayAllBooks();
        library.checkOutBook(programmingBook);
        library.displayAllBooks();
        library.returnBook(wrongBook);
        library.displayAllBooks();

        assertEquals(defaultBooksListString() + NEW_LINE + SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + getSecondBookString() + NEW_LINE + FAIL_RETURN_MESSAGE + NEW_LINE + getSecondBookString(), consoleOutContent.toString().trim());
    }

    public List<Book> generateTempBooks() {
        Book book1 = new Book("Programming Book 1", 2000, "Author Not Found");
        Book book2 = new Book("Programming Book 2", 2001, "Author Not Found");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        return books;
    }

    public String defaultBooksListString() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Author Not Found\n" +
                "2. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Author Not Found";
    }

    public String getFirstBookString() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Author Not Found";
    }

    public String getSecondBookString() {
        return "1. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Author Not Found";
    }

}