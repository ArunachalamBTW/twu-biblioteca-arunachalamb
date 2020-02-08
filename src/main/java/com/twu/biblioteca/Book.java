package com.twu.biblioteca;

import com.twu.biblioteca.config.CONSTANTS;

public class Book {
    private String name;
    private int year;
    private String author;
    private boolean isCheckedOut = false;

    public Book(String name, int year, String author) {
        this.name = name;
        this.year = year;
        this.author = author;
    }

    public void displayName() {
        System.out.println(name);
    }

    public void displayBookDetails() {
        if (!isCheckedOut) {
            System.out.println(name + "\t|\t" + year + "\t|\t" + author);
        }
    }

    public void checkOut() {
        if (!isCheckedOut) {
            isCheckedOut = true;
            System.out.println(CONSTANTS.CHECKOUT_SUCCESS_MESSAGE);
        }
    }

}
