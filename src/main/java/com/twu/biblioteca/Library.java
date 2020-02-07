package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> booksList;

    public Library(List<Book> booksList) {
        this.booksList = booksList;
    }

    public void displayAllBooks() {
        int countOfBooks = 1;
        for (Book book: booksList) {
            System.out.print(countOfBooks++ + ". ");
            book.displayBookDetails();
        }
    }
}
