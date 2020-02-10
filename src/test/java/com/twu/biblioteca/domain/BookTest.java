package com.twu.biblioteca.domain;

import com.twu.biblioteca.domain.Book; // TODO - unused imports - HYGIENE - take care.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static com.twu.biblioteca.config.CONSTANTS.*;

class BookTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream(); // TODO - ouch.

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @Test // TODO - really bad test.
    void shouldShowBookName() { // TODO - What is SHOW? You need to be careful about updating your test names. Otherwise they'll be useless.
        String bookName = "Programming Book";
        Book programmingBook = new Book(bookName, 2000, "No Author");

        assertEquals(bookName, programmingBook.getName()); // TODO - do we test getters? Or rather, what do we want to test? Behaviour. Who cares about getters?
    }

    @Test // TODO - good that you're testing behaviour here.
    void shouldShowBookDetails() {
        String bookName = "Programming Book";
        String authorName = "No Author"; // TODO - why?
        int year = 2000;
        Book programmingBook = new Book(bookName, year, authorName);

        assertEquals(bookName + BOOK_DETAILS_SEPARATORS + year + BOOK_DETAILS_SEPARATORS + authorName, programmingBook.getBookDetails()); // TODO - code smell - Long line - extract it
    }

    @Test // TODO - alright.
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
        Book book = generateRandomBook(1234); // TODO - how does this helper function help me? - This function is non intutive. I have to look into the method implementation when I read it for the first time.

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