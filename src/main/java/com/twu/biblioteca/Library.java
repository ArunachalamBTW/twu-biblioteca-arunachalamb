package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> booksList;

    public Library(List<Book> booksList) {
        this.booksList = booksList;
    }

    public List<Book> getBooks() {
        return booksList;
    }

    public List<String> getNamesOfAllBooks() {
        List<String> bookNames = new ArrayList<>();

        return bookNames;
    }

}
