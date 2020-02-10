package com.twu.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.config.CONSTANTS.*;
import static com.twu.biblioteca.console.Screen.displayMessage;

public class Library {
    private List<Book> booksList; // TODO - name - BOOKS - Confusing as well. Is this list of all books? List of unchecked books?
    private List<Book> checkOutBooks; // TODO - name again - probably will use past tense

    public Library(List<Book> booksList) {
        this.booksList = booksList;
        this.checkOutBooks = new ArrayList<>();
    }

    public String displayAllBooks() { // TODO - what's display? Liar. // TODO - why ALL?
        StringBuilder allBookDetails = new StringBuilder();
        int countOfBooks = 1;
        for (Book book : booksList) {
            String bookDetails = "";
            if (!checkOutBooks.contains(book)) {
                bookDetails += countOfBooks++ + ". " + book.getDetails() + NEW_LINE;
            }
            allBookDetails.append(bookDetails);
        }
        return allBookDetails.toString();
    }

    // TODO - checkout is probably one word
    public void checkOutBook(String bookName) { // TODO - name again. What else will I checkout from library? Libraian?
        // TODO - read about streams, and try to use implicit loops - But don't spend too much time.
        for (Book book : booksList) { // TODO - can we get rid of the loop? - When I say this, I mean explicit loop.
            if (!checkOutBooks.contains(book) && book.isSameByName(bookName)) { // TODO - can you name your conditions?
                checkOutBooks.add(book);
                notifyUser(SUCCESS_CHECKOUT_MESSAGE);
                return;
            }
        }
        notifyUser(FAIL_CHECKOUT_MESSAGE);
    }


    public void returnBook(String bookName) {
        for (Book book : checkOutBooks) {
            if (book.isSameByName(bookName)) {
                checkOutBooks.remove(book);
                notifyUser(SUCCESS_RETURN_MESSAGE);
                return;
            }
        }
        notifyUser(FAIL_RETURN_MESSAGE);
    }

    public void notifyUser(String message) {
        displayMessage(message);
    } // TODO - using statics. What is the problem? Library is a liar. - Its needs sout to work. Doesn't say so.

}
