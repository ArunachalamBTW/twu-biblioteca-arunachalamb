package com.twu.biblioteca;

import com.twu.biblioteca.config.CONSTANTS;

public class Book {
    private String name;
    private int year;
    private String author;

    public Book(String name, int year, String author) {
        this.name = name;
        this.year = year;
        this.author = author;
    }

    public void displayName() {
        System.out.println(name);
    }

    public void displayBookDetails() {
        System.out.println(name + "\t|\t" + year + "\t|\t" + author);
    }

    public boolean isSameBookByName(String bookName) {
        return name.equals(bookName);
    }
}
