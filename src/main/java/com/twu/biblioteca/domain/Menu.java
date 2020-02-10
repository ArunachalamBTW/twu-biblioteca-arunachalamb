package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;

import static com.twu.biblioteca.config.GlobalConstants.*;
import static com.twu.biblioteca.controller.ControllerConstants.*;
import static com.twu.biblioteca.domain.DomainConstants.*;

public class Menu {

    private Library library;

    public Menu(Library library) {
        this.library = library;
    }

    public void mainMenu() {
        int userChoice = 0;
        boolean quit = false;

        while (!quit) {
            Screen.getInstance().displayMenu();
            userChoice = Input.createInstance().getIntegerInput();
            switch (userChoice) {
                case MAIN_MENU_DISPLAY_ALL_BOOKS:
                    Screen.getInstance().displayMessage(library.getBooks()); // TODO - how are the books being printed?
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
                    Screen.getInstance().notifyUser(INVALID_OPTION);
                    break;
            }
        }
    }

    private void returnABook() {
        Screen.getInstance().notifyUser(GET_BOOK_NAME);
        library.returnBook(Input.createInstance().getStringInput());
    }

    private void checkOutABook() {
        Screen.getInstance().notifyUser(GET_BOOK_NAME);
        library.checkout(Input.createInstance().getStringInput());
    }

}
