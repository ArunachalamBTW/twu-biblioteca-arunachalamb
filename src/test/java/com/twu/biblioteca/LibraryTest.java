package com.twu.biblioteca;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class LibraryTest {

    @Test
    public void shouldReturnEmptyBooksListWhenNoBooksInLibrary() {
        Library library = new Library(new ArrayList<>());

        List<Book> books = library.getBooks();

        assertEquals(0, books.size());
    }

    @Test
    void shouldReturnOneBookWhichIsInLibrary() {
        Book book = mock(Book.class);
        Library library = new Library(Collections.singletonList(book));

        List<Book> books = library.getBooks();

        assertEquals(1, books.size());
    }

    @Test
    void shouldCompareTheOnlyOneBookName() {
        Book book = mock(Book.class);
        Library library = new Library(Collections.singletonList(book));
        doReturn("Programming Book").when(book).getName();

        List<Book> books = library.getBooks();

        assertEquals("Programming Book", books.get(0).getName());
    }
}