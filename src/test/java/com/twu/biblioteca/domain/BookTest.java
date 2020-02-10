package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Input;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.twu.biblioteca.config.GlobalConstants.*;

class BookTest {

    @Test // TODO - good that you're testing behaviour here.
    void shouldReturnBookDetailsWithDefaultSeparator() {
        String bookName = "Programming Book";
        String authorName = "Martin Fowler"; // TODO - why?
        int year = 2000;
        Book programmingBook = new Book(bookName, year, authorName);

        String expected = bookName + BOOK_DETAILS_SEPARATORS + year + BOOK_DETAILS_SEPARATORS + authorName;
        assertEquals(expected, programmingBook.getDetails()); // TODO - code smell - Long line - extract it
    }

    @Test // TODO - alright.
    void shouldShowBookDetailsWithSpecifiedSeparator() {
        String bookName = "Programming Book";
        String authorName = "Martin Fowler";
        int year = 2000;
        String separator = " - ";
        Book programmingBook = new Book(bookName, year, authorName);

        assertEquals(bookName + separator + year + separator + authorName, programmingBook.getDetails(separator));
    }

    @Test
    void shouldReturnTrueForBookWithSameName() {
        String bookName = "Programming Book 1";
        Book book = getBook(); // TODO - how does this helper function help me? - This function is non intutive. I have to look into the method implementation when I read it for the first time.

        assertTrue(book.isSameByName(bookName));
    }

    @Test
    void shouldReturnFalseForBookWithDifferentName() {
        String differentBookName = "Programming Book 2";
        Book book = getBook();

        assertFalse(book.isSameByName(differentBookName));
    }

    public Book getBook() {
        return new Book("Programming Book 1", 2000, "Martin Fowler");
    }
}