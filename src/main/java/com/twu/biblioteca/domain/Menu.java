package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.menu_options.MenuOptionCheckout;
import com.twu.biblioteca.domain.menu_options.MenuOptionLibraryBooks;
import com.twu.biblioteca.domain.menu_options.MenuOptionReturnBook;
import com.twu.biblioteca.domain.menu_options.MenuOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.twu.biblioteca.controller.ControllerConstants.*;

public class Menu {

    private Library library;
    private Screen screen;

    public Menu(Library library, Screen screen) {
        this.library = library;
        this.screen = screen;
    }

    public void mainMenu() {
        List<MenuOptions> menuOptions = getMenuOptions();
        int userChoice = -1;

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
        return new ArrayList<>(Arrays.asList(new MenuOptionLibraryBooks(), new MenuOptionCheckout(), new MenuOptionReturnBook()));
    }

}
