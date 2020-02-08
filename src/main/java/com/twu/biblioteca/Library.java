package com.twu.biblioteca;

import com.twu.biblioteca.config.CONSTANTS;

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
            System.out.print(countOfBooks++ + ". ");
            book.displayBookDetails();
        }
    }

    public void checkOutBook(String bookName) {
        for (Book book : booksList) {
            if (book.isSameBookByName(bookName)) {
                checkOutBooks.add(book);
                booksList.remove(book);
                System.out.println(CONSTANTS.CHECKOUT_SUCCESS_MESSAGE);
            }
        }
    }

}
