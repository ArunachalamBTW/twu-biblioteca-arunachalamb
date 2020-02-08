package com.twu.biblioteca;

import static com.twu.biblioteca.config.CONSTANTS.*;
import static com.twu.biblioteca.io.Screen.displayMessage;
import static com.twu.biblioteca.io.Screen.displayMessageOneLine;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> booksList;
    private List<Book> checkOutBooks;

    public Library(List<Book> booksList) {
        this.booksList = booksList;
        this.checkOutBooks = new ArrayList<>();
    }

    public void displayAllBooks() {
        int countOfBooks = 1;
        for (Book book: booksList) {
            if (!checkOutBooks.contains(book)) {
                displayMessageOneLine(countOfBooks++ + ". ");
                book.displayBookDetails();
            }
        }
    }

    public void checkOutBook(String bookName) {
        for (Book book : booksList) {
            if (!checkOutBooks.contains(book) && book.isSameBookByName(bookName)) {
                checkOutBooks.add(book);
                notifyUser(SUCCESS_CHECKOUT_MESSAGE);
                return;
            }
        }
        notifyUser(FAIL_CHECKOUT_MESSAGE);
    }


    public void returnBook(String bookName) {
        for (Book book : checkOutBooks) {
            if (book.isSameBookByName(bookName)) {
                checkOutBooks.remove(book);
                notifyUser(SUCCESS_RETURN_MESSAGE);
                return;
            }
        }
        notifyUser(FAIL_RETURN_MESSAGE);
    }

    public void notifyUser(String message) {
        displayMessage(message);
    }

}
