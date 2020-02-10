package com.twu.biblioteca.controller;

import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.domain.Menu;

import static com.twu.biblioteca.config.GlobalConstants.*;
import static com.twu.biblioteca.controller.ControllerConstants.*;

import java.util.ArrayList;
import java.util.List;

// TODO - think about - SRP, OCP for this class. List it down.
public class BibliotecaApp {

    private Library library;

    public BibliotecaApp() {
        initializeLibrary();
    }

    public static void main(String[] args) {
        new BibliotecaApp().start();
    }

    void start() {
        Screen.getInstance().displayWelcomeMessage();
//        Use Menu implementation here - TODO what does that mean? Try doing it and show it again.
        new Menu(library).mainMenu();
    }

    private void initializeLibrary() {
        // TODO - why not inline?
        library = new Library(getDefaultBooks());
    }

    private List<Book> getDefaultBooks() {
        Book book1 = new Book("Programming Book 1", 2000, "Martin Fowler");
        Book book2 = new Book("Programming Book 2", 2001, "Martin Fowler");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        return books;
    }
}
