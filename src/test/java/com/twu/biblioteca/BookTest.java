package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void shouldCheckOutABook() {
        Book book = mock(Book.class);

        book.checkOut();

        verify(book, times(1)).checkOut();
    }

    @Test
    void shouldShowSuccessStatusInConsoleWhenABookIsCheckedOutSuccessfully() {
        Book book = new Book("Programming Book 1", 2000, "Author Not Defined");

        book.checkOut();

        assertEquals("Thank you! Enjoy the book", consoleOutContent.toString().trim());
    }
}