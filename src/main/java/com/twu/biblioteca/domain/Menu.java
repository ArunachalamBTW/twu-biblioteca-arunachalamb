package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.twu.biblioteca.controller.ControllerConstants.*;

public class Menu {

    private Library library;

    public Menu(Library library) {
        this.library = library;
    }

    public void mainMenu() {
        List<MenuOptions> menuOptions = getMenuOptions();
        int userChoice = -1;

        while (true) {
            Screen.getInstance().displayMenu();

            try {
                userChoice = Input.createInstance().getIntegerInput();
            } catch (NumberFormatException exception) {
                Screen.getInstance().notifyUser(INVALID_OPTION);
                continue;
            }

            if (userChoice == menuOptions.size() + 1) {
                break;
            } else if (userChoice > menuOptions.size() + 1 || userChoice <= 0) {
                Screen.getInstance().notifyUser(INVALID_OPTION);
            } else {
                menuOptions.get(userChoice - 1).execute(library);
            }
        }
    }

    private List<MenuOptions> getMenuOptions() {
        return new ArrayList<>(Arrays.asList(new MenuOptionLibraryBooks(), new MenuOptionCheckout(), new MenuOptionReturnBook()));
    }

}
