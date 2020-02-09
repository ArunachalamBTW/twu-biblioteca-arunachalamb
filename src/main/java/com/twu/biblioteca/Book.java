package com.twu.biblioteca;

import static com.twu.biblioteca.config.CONSTANTS.*;

public class Book {
    private String name;
    private int year;
    private String author;

    public Book(String name, int year, String author) {
        this.name = name;
        this.year = year;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getBookDetails() {
        return name + BOOK_DETAILS_SEPARATORS + year + BOOK_DETAILS_SEPARATORS + author;
    }

    public boolean isSameBookByName(String bookName) {
        return name.equals(bookName);
    }
}
