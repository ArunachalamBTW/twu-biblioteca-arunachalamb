package com.twu.biblioteca.domain;

import static com.twu.biblioteca.config.CONSTANTS.*;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
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
    void shouldShowAllBooksInALibrary() { // TODO - is this the simplest test?
        Library library = new Library(generateTempBooks()); // TODO - intent behind helper method is probably good, however its creating more problem then helping at this point in time.

        assertEquals(defaultBooksListString(), library.displayAllBooks().trim()); // TODO - why trim? Apple does not fall. Change your physics.
    }

    @Test
    void shouldCheckOutBookWithSameName() {
        Library library = new Library(generateTempBooks());
        String bookDetails = "";
        bookDetails += library.displayAllBooks();

        library.checkOutBook("Programming Book 1");

        bookDetails += library.displayAllBooks();
        assertEquals(SUCCESS_CHECKOUT_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListString() + NEW_LINE + getSecondBookString(), bookDetails.trim());
    }

    @Test
    void shouldFailWhenWithSameNameIsNotFound() {
        Library library = new Library(generateTempBooks());
        String bookDetails = "";

        bookDetails += library.displayAllBooks();
        library.checkOutBook("Random Book");
        bookDetails += library.displayAllBooks();

        assertEquals(FAIL_CHECKOUT_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListString() + NEW_LINE + defaultBooksListString(), bookDetails.trim());
    }

    @Test
    void shouldReturnTheBookToTheLibrary() {
        String programmingBook = "Programming Book 1";
        Library library = new Library(generateTempBooks());
        String bookDetails = "";

        bookDetails += library.displayAllBooks();
        library.checkOutBook(programmingBook);
        bookDetails += library.displayAllBooks();
        library.returnBook(programmingBook);
        bookDetails += library.displayAllBooks();

        assertEquals(SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + SUCCESS_RETURN_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListString() + NEW_LINE + getSecondBookString() + NEW_LINE + defaultBooksListString(), bookDetails.trim());
    }

    @Test
    void shouldNotReturnAWrongBook() {
        String programmingBook = "Programming Book 1";
        String wrongBook = "Prog Book 1";
        Library library = new Library(generateTempBooks());
        String bookDetails = "";

        bookDetails += library.displayAllBooks();
        library.checkOutBook(programmingBook);
        bookDetails += library.displayAllBooks();
        library.returnBook(wrongBook);
        bookDetails += library.displayAllBooks();

        assertEquals(SUCCESS_CHECKOUT_MESSAGE + NEW_LINE + FAIL_RETURN_MESSAGE, consoleOutContent.toString().trim());
        assertEquals(defaultBooksListString() + NEW_LINE + getSecondBookString() + NEW_LINE + getSecondBookString(), bookDetails.trim());
    }

    public List<Book> generateTempBooks() { // TODO - temp is a horrible word. Why generate? Why not get? Why not just, twoDifferentBooks() ? Why is this a method? - Helper method? Why not simply a static constant?
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