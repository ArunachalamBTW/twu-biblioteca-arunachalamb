package com.twu.biblioteca;

import com.twu.biblioteca.config.CONSTANTS;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        new BibliotecaApp().start();
    }

    void start() {
        displayWelcomeMessage();
        displayMenu();
        processUserInput();
    }

    private void processUserInput() {
        Scanner input = new Scanner(System.in);
        int userChoice;
        boolean quit = false;
        while(!quit) {
            userChoice = input.nextInt();
            switch (userChoice) {
                case 1:
                    List<Book> defaultBooks = getDefaultBooks();
                    Library library = new Library(defaultBooks);
                    library.displayAllBooks();
                    break;
                case 2:
                    quit = true;
                    break;
                default:
                    notifyUser(CONSTANTS.INVALID_OPTION);
                    displayMenu();
                    break;
            }
        }
    }

    private void notifyUser(String invalidMessage) {
        System.out.println(invalidMessage);
    }

    List<Book> getDefaultBooks() {
        Book book1 = new Book("Programming Book 1", 2000, "Author Not Found");
        Book book2 = new Book("Programming Book 2", 2001, "Author Not Found");
        return Arrays.asList(book1, book2);
    }

    void displayWelcomeMessage() {
        System.out.println(CONSTANTS.WELCOME_MESSAGE);
    }

    void displayMenu() {
        System.out.println(CONSTANTS.MENU_OPTIONS);
    }
}
