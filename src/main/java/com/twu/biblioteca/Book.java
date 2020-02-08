package com.twu.biblioteca;

import static com.twu.biblioteca.io.Screen.displayMessage;

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
        displayMessage(name);
    }

    public void displayBookDetails() {
        displayMessage(name + "\t|\t" + year + "\t|\t" + author);
    }

    public boolean isSameBookByName(String bookName) {
        return name.equals(bookName);
    }
}
