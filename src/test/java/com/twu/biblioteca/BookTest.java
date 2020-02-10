package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static com.twu.biblioteca.config.CONSTANTS.*;

class BookTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @Test
    void shouldShowBookName() {
        String bookName = "Programming Book";
        Book programmingBook = new Book(bookName, 2000, "No Author");

        assertEquals(bookName, programmingBook.getName());
    }

    @Test
    void shouldShowBookDetails() {
        String bookName = "Programming Book";
        String authorName = "No Author";
        int year = 2000;
        Book programmingBook = new Book(bookName, year, authorName);

        assertEquals(bookName + BOOK_DETAILS_SEPARATORS + year + BOOK_DETAILS_SEPARATORS + authorName, programmingBook.getBookDetails());
    }

    @Test
    void shouldShowBookDetailsWithSpecifiedSeparators() {
        String bookName = "Programming Book";
        String authorName = "No Author";
        int year = 2000;
        String separator = " - ";
        Book programmingBook = new Book(bookName, year, authorName);

        assertEquals(bookName + separator + year + separator + authorName, programmingBook.getBookDetails(separator));
    }

    @Test
    void shouldReturnTrueForBookWithSameName() {
        String randomBook = "Random Book 1234";
        Book book = generateRandomBook(1234);

        assertTrue(book.isSameBookByName(randomBook));
    }

    @Test
    void shouldReturnFalseForBookWithDifferentName() {
        String randomBookName = "Random Book 5678";
        Book book = generateRandomBook(1234);

        assertFalse(book.isSameBookByName(randomBookName));
    }

    public Book generateRandomBook(int random) {
        return new Book("Random Book " + random, random, "Author " + random);
    }
}