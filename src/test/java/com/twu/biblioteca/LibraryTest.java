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

        assertEquals("1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n2. Programming Book 2\t|\t2001\t|\tAuthor Not Found", consoleOutContent.toString().trim());
    }

    @Test
    void shouldCheckOutBookWithSameName() {
        Library library = new Library(generateTempBooks());

        library.displayAllBooks();
        library.checkOutBook("Programming Book 1");
        library.displayAllBooks();

        assertEquals("1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n2. Programming Book 2\t|\t2001\t|\tAuthor Not Found\n" + SUCCESS_CHECKOUT_MESSAGE + "\n1. Programming Book 2\t|\t2001\t|\tAuthor Not Found", consoleOutContent.toString().trim());
    }

    @Test
    void shouldFailWhenWithSameNameIsNotFound() {
        Library library = new Library(generateTempBooks());

        library.displayAllBooks();
        library.checkOutBook("Random Book");
        library.displayAllBooks();

        assertEquals("1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n2. Programming Book 2\t|\t2001\t|\tAuthor Not Found\n" + FAIL_CHECKOUT_MESSAGE + "\n1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n2. Programming Book 2\t|\t2001\t|\tAuthor Not Found", consoleOutContent.toString().trim());
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

        assertEquals("1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n2. Programming Book 2\t|\t2001\t|\tAuthor Not Found\n" + SUCCESS_CHECKOUT_MESSAGE + "\n1. Programming Book 2\t|\t2001\t|\tAuthor Not Found\n" + SUCCESS_RETURN_MESSAGE + "\n1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n2. Programming Book 2\t|\t2001\t|\tAuthor Not Found", consoleOutContent.toString().trim());
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

        assertEquals("1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n2. Programming Book 2\t|\t2001\t|\tAuthor Not Found\n" + SUCCESS_CHECKOUT_MESSAGE + "\n1. Programming Book 2\t|\t2001\t|\tAuthor Not Found\n" + FAIL_RETURN_MESSAGE + "\n1. Programming Book 2\t|\t2001\t|\tAuthor Not Found", consoleOutContent.toString().trim());
    }

    public List<Book> generateTempBooks() {
        Book book1 = new Book("Programming Book 1", 2000, "Author Not Found");
        Book book2 = new Book("Programming Book 2", 2001, "Author Not Found");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        return books;
    }

}