package com.twu.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.domain.DomainConstants.*;
import static com.twu.biblioteca.config.GlobalConstants.*;
import static com.twu.biblioteca.console.Screen.displayMessage;

public class Library {
    private List<Book> books; // TODO - name - BOOKS - Confusing as well. Is this list of all books? List of unchecked books?
    private List<Book> checkedOutBooks; // TODO - name again - probably will use past tense

    public Library(List<Book> books) {
        this.books = books;
        this.checkedOutBooks = new ArrayList<>();
    }

    public String getBooks() { // TODO - what's display? Liar. // TODO - why ALL?
        StringBuilder allBookDetails = new StringBuilder();
        int countOfBooks = 1;
        for (Book book : books) {
            String bookDetails = "";
            if (!checkedOutBooks.contains(book)) {
                bookDetails += countOfBooks++ + PERIOD + SPACE + book.getDetails() + NEW_LINE;
            }
            allBookDetails.append(bookDetails);
        }
        return allBookDetails.toString();
    }

    // TODO - checkout is probably one word
    public void checkout(String bookName) { // TODO - name again. What else will I checkout from library? Libraian?
        // TODO - read about streams, and try to use implicit loops - But don't spend too much time.
//        Book checkoutBook = books.stream().filter(book -> book.isSameByName(bookName)).filter(book -> !checkedOutBooks.contains(book)).findFirst();
        for (Book book : books) { // TODO - can we get rid of the loop? - When I say this, I mean explicit loop.
            boolean isBookAvailable = !checkedOutBooks.contains(book);
            boolean isSameBook = book.isSameByName(bookName);
            if (isBookAvailable && isSameBook) { // TODO - can you name your conditions?
                checkedOutBooks.add(book);
                notifyUser(SUCCESS_CHECKOUT_MESSAGE);
                return;
            }
        }
        notifyUser(FAIL_CHECKOUT_MESSAGE);
    }


    public void returnBook(String bookName) {
        for (Book book : checkedOutBooks) {
            boolean isSameBook = book.isSameByName(bookName);
            if (isSameBook) {
                checkedOutBooks.remove(book);
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
