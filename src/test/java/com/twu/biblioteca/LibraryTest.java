package com.twu.biblioteca;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {

    @Test
    public void shouldReturnEmptyBooksListWhenNoBooksInLibrary() {
        Library library = new Library(new ArrayList<>());

        List<Book> books = library.getBooks();

        assertEquals(0, books.size());
    }

}