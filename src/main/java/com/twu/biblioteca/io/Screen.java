package com.twu.biblioteca.io;

import com.twu.biblioteca.config.CONSTANTS;

public class Screen {
    public static void displayWelcomeMessage() {
        System.out.println(CONSTANTS.WELCOME_MESSAGE);
    }

    public static void displayMenu() {
        System.out.println(CONSTANTS.MENU_OPTIONS);
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