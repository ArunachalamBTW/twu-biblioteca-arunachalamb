package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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

        programmingBook.displayName();

        assertEquals(bookName, consoleOutContent.toString().trim());
    }

    @Test
    void shouldShowBookDetails() {
        String bookName = "Programming Book";
        String authorName = "No Author";
        int year = 2000;
        Book programmingBook = new Book(bookName, year, authorName);

        programmingBook.displayBookDetails();

        assertEquals(bookName + "\t|\t" + year + "\t|\t" + authorName, consoleOutContent.toString().trim());
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