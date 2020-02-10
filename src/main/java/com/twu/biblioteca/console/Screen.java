package com.twu.biblioteca.console;

import static com.twu.biblioteca.console.ConsoleConstants.*;

public class Screen {
    public static void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void displayMenu() {
        System.out.println(MENU_OPTIONS); // TODO - static imports
    }

    public static void notifyUser(String message) {
        System.out.println(message);
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayMessageOneLine(String message) {
        System.out.print(message);
    }
}
