package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.controller.Login;
import com.twu.biblioteca.domain.menu_options.*;
import com.twu.biblioteca.services.Notification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.twu.biblioteca.controller.ControllerConstants.*;

public class Menu implements Notification {

    private Library library;
    private Screen screen;
    private User loggedInUser;
    private Login login;

    public Menu(Library library, Screen screen) {
        this.library = library;
        this.screen = screen;
    }

    public void mainMenu(Login login) {
        this.login = login;
        List<MenuOptions> menuOptions = getMenuOptions();
        int userChoice;

        while (true) {
            screen.displayMenu();

            try {
                userChoice = Input.createInstance().getIntegerInput();
            } catch (NumberFormatException exception) {
                screen.notifyUser(INVALID_OPTION);
                continue;
            }

            if (userChoice == menuOptions.size() + 1) {
                break;
            } else if (userChoice > menuOptions.size() + 1 || userChoice <= 0) {
                screen.notifyUser(INVALID_OPTION);
            } else {
                menuOptions.get(userChoice - 1).execute(library);
            }
        }
    }

    private List<MenuOptions> getMenuOptions() {
        return new ArrayList<>(Arrays.asList(
                new MenuOptionBooks(),
                new MenuOptionCheckout(),
                new MenuOptionReturnBook(),
                new MenuOptionMovies(),
                new MenuOptionCheckoutMovie(),
                new MenuOptionLogin(login),
                new MenuOptionCheckedoutBooks()
        ));
    }

    @Override
    public void loggedIn(User user) {
        loggedInUser = user;
    }

    @Override
    public void loggedOut() {
        loggedInUser = null;
    }
}
