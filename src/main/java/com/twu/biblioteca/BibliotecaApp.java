package com.twu.biblioteca;

import com.twu.biblioteca.config.CONSTANTS;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.twu.biblioteca.io.Screen.*;

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
        int userChoice = 0;
        boolean quit = false;

        while(!quit) {
            displayMenu();
            try { userChoice = input.nextInt(); } catch (Exception e) { input.nextLine(); }
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
