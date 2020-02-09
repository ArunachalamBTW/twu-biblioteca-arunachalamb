package com.twu.biblioteca;

import com.twu.biblioteca.io.Input;

import static com.twu.biblioteca.config.CONSTANTS.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.twu.biblioteca.io.Screen.*;

public class BibliotecaApp {

    private Library library;

    public BibliotecaApp() {
        initializeLibrary();
    }

    public static void main(String[] args) {
        new BibliotecaApp().start();
    }

    void start() {
        displayWelcomeMessage();
        processUserInput();
    }

    private void processUserInput() {
        int userChoice = 0;
        boolean quit = false;

        while (!quit) {
            displayMenu();
            try {
                userChoice = Input.createInstance().getIntegerInput();
            } catch (Exception e) {
                Input.createInstance().getStringInput();
            }
            switch (userChoice) {
                case MAIN_MENU_DISPLAY_ALL_BOOKS:
                    library.displayAllBooks();
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
        library.checkOutBook(Input.createInstance().getStringInput());
    }

    private void initializeLibrary() {
        List<Book> defaultBooks = getDefaultBooks();
        library = new Library(defaultBooks);
    }

    private List<Book> getDefaultBooks() {
        Book book1 = new Book("Programming Book 1", 2000, "Author Not Found");
        Book book2 = new Book("Programming Book 2", 2001, "Author Not Found");
        return Arrays.asList(book1, book2);
    }
}
