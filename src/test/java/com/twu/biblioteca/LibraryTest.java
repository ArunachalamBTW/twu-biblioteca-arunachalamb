package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @Test
    void shouldShowAllBooksInALibrary() {
        Book book1 = new Book("Programming Book 1");
        Book book2 = new Book("Programming Book 2");
        Library library = new Library(Arrays.asList(book1, book2));

        library.displayAllBooks();

        assertEquals("1. Programming Book 1\n2. Programming Book 2", consoleOutContent.toString().trim());
    }
}