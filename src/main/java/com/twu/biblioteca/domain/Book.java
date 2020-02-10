package com.twu.biblioteca.domain;

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

    public String getBookDetails(String separators) {
        return name + separators + year + separators + author;
    }

    // TODO - unnecessary `book inside getBookDetails
    public String getBookDetails() {
        return getBookDetails(BOOK_DETAILS_SEPARATORS);
    }

    // TODO - call it, isSimilar is possible. However hard. isSameByName
    public boolean isSameBookByName(String bookName) {
        return name.equals(bookName);
    }
}
