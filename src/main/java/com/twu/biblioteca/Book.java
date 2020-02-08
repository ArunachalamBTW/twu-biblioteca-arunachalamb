package com.twu.biblioteca;

public class Book {
    String name;
    int year;
    String author;

    public Book(String name, int year, String author) {
        this.name = name;
        this.year = year;
        this.author = author;
    }

    public void displayName() {
        System.out.println(name);
    }

    public void displayBookDetails() {
        System.out.println(name+"\t|\t"+year+"\t|\t"+author);
    }

}
