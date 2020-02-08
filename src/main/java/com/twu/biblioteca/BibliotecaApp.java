package com.twu.biblioteca;

import com.twu.biblioteca.config.CONSTANTS;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private Library library;

    public static void main(String[] args) {
        new BibliotecaApp().start();
    }

    void start() {
        initializeLibrary();
        displayWelcomeMessage();
        processUserInput();
    }

    private void processUserInput() {
        Scanner input = new Scanner(System.in);
        int userChoice;
        boolean quit = false;
        while(!quit) {
            displayMenu();
            userChoice = input.nextInt();
            switch (userChoice) {
                case 1:
                    library.displayAllBooks();
                    break;
                case 2:
                    checkOutABook();
                    break;
                case 3:
                    returnABook();
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    notifyUser(CONSTANTS.INVALID_OPTION);
                    break;
            }
        }
    }

    private void returnABook() {
        notifyUser("Enter a book name: ");
        library.returnBook(new Scanner(System.in).nextLine());
    }

    private void checkOutABook() {
        notifyUser("Enter a book name: ");
        library.checkOutBook(new Scanner(System.in).nextLine());
    }

    private void notifyUser(String message) {
        System.out.println(message);
    }

    private void initializeLibrary() {
        List<Book> defaultBooks = getDefaultBooks();
        library = new Library(defaultBooks);
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
