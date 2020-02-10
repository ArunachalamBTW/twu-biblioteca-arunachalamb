package com.twu.biblioteca.controller;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.console.Input;

import static com.twu.biblioteca.config.CONSTANTS.*;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.console.Screen.*;

// TODO - think about - SRP, OCP for this class. List it down.
public class BibliotecaApp {

    private Library library;

    public BibliotecaApp() {
        initializeLibrary();
//        Use Menu implementation here - TODO what does that mean? Try doing it and show it again.
    }

    public static void main(String[] args) {
        new BibliotecaApp().start();
    }

    void start() {
        displayWelcomeMessage(); // TODO - come back to this
        processUserInput();
    }

    private void processUserInput() {
        int userChoice = 0;
        boolean quit = false;

        while (!quit) {
            displayMenu();
            userChoice = Input.createInstance().getIntegerInput();
            switch (userChoice) {
                case MAIN_MENU_DISPLAY_ALL_BOOKS:
                    displayMessage(library.getBooks()); // TODO - how are the books being printed?
                    break;
                case MAIN_MENU_CHECKOUT_A_BOOK:
                    checkOutABook();
                    break;
                case MAIN_MENU_RETURN_A_BOOK:
                    returnABook();
                    break;
                case MAIN_MENU_QUIT:
                    quit = true;
                    break;
                default:
                    notifyUser(INVALID_OPTION);
                    break;
            }
        }

    }

    private void returnABook() {
        notifyUser("Enter a book name: ");
        library.returnBook(Input.createInstance().getStringInput());
    }

    private void checkOutABook() {
        notifyUser("Enter a book name: ");
        library.checkout(Input.createInstance().getStringInput());
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
