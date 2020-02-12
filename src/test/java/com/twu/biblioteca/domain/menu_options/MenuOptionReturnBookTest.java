package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.twu.biblioteca.config.GlobalConstants.BOOK_DETAILS_SEPARATORS;
import static org.mockito.Mockito.*;

class MenuOptionReturnBookTest {
    private ByteArrayOutputStream consoleOutContent;
    private PrintStream printStream;
    private List<Book> books;
    private Library library;
    private Screen screen = Screen.getInstance();
    private Library mockedLibrary;

    @BeforeAll
    public static void initTest() {

    }

    @BeforeEach
    public void setUpStreams() {
        printStream = System.out;
        consoleOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutContent));
        mockedLibrary = mock(Library.class);

        Book book1 = new Book("Programming Book 1", 2000, "Martin Fowler");
        Book book2 = new Book("Programming Book 2", 2001, "Martin Fowler");
        books = new ArrayList<>(Arrays.asList(book1, book2));
        library = new Library(books, screen);
    }

    @AfterEach
    public void setOutStreams() {
        System.setOut(printStream);
        Input.reset();
        Screen.reset();
    }

    @Test
    void shouldNotReturnAInValidBook() {
        InputStream sysInBackup = System.in;
        String bookName = "Programming Book 1";
        ByteArrayInputStream input1 = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(input1);
        MenuOptions returnBook = new MenuOptionReturnBook();

        returnBook.execute(mockedLibrary);

        verify(mockedLibrary, times(1)).returnBook(bookName);
        System.setIn(sysInBackup);
    }

    @Test
    void shouldReturnAValidBook() {
        InputStream sysInBackup = System.in;
        String bookName = "Programming Book 1";
        String bookNameCombined = bookName + "\n" + bookName;
        ByteArrayInputStream input1 = new ByteArrayInputStream(bookNameCombined.getBytes());
        System.setIn(input1);
        MenuOptions checkoutBook = new MenuOptionCheckout();
        MenuOptions returnBook = new MenuOptionReturnBook();
        checkoutBook.execute(mockedLibrary);

        returnBook.execute(mockedLibrary);

        verify(mockedLibrary, times(1)).checkout(bookName);
        verify(mockedLibrary, times(1)).returnBook(bookName);
        System.setIn(sysInBackup);
    }

    public String defaultBookListDetails() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Martin Fowler\n" +
                "2. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }

}