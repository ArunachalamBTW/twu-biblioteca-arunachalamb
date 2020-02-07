package com.twu.biblioteca;

import java.util.List;

public class Library {
    private List<Book> booksList;

    public Library(List<Book> booksList) {
        this.booksList = booksList;
    }

    public List<Book> getBooks() {
        return booksList;
    }
}